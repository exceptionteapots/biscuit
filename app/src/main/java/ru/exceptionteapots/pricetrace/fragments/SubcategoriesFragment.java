package ru.exceptionteapots.pricetrace.fragments;

import static ru.exceptionteapots.pricetrace.NetworkService.hasConnection;

import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.exceptionteapots.pricetrace.pojo.Category;
import ru.exceptionteapots.pricetrace.MainActivity;
import ru.exceptionteapots.pricetrace.NetworkService;
import ru.exceptionteapots.pricetrace.R;

public class SubcategoriesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private List<Category> data = new ArrayList<>();
    private CategoryAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private int parentID;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subcategories, container, false);

        ((MainActivity) requireActivity()).setChosenFragment(0);
        mSwipeRefreshLayout = view.findViewById(R.id.subcategories_refresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.dark_red);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        RecyclerView recyclerView = view.findViewById(R.id.subcategories_list);
        parentID = SubcategoriesFragmentArgs.fromBundle(getArguments()).getCategoryID();

        adapter = new CategoryAdapter(getContext(), data, recyclerView, false);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter( adapter );
        mSwipeRefreshLayout.setRefreshing(true);

        onRefresh();
        return view;
    }


    /**
     * Функция отображения дочерних категорий при обновлении экрана
     * **/
    @Override
    public void onRefresh() {
        // отображение дочерних категорий
        if (!hasConnection(getContext())) {
            new MaterialAlertDialogBuilder(getContext())
                    .setTitle(getString(R.string.network_error_title))
                    .setMessage(getString(R.string.network_error_message))
                    .setIcon(R.drawable.ic_cancel)
                    .setPositiveButton(getString(R.string.ok), (dialogInterface, i) -> {

                    })
                    .show();
            mSwipeRefreshLayout.setRefreshing(false);
        }
        Call<List<Category>> call = NetworkService.getInstance().getPriceTraceAPI().getSubcategoryByParentId(parentID);
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(@NonNull Call<List<Category>> call, @NonNull Response<List<Category>> response) {
                List<Category> list = response.body();
                data.addAll(list);
                adapter.notifyDataSetChanged();
                data = new ArrayList<>();
                mSwipeRefreshLayout.setRefreshing(false);
            }
            @Override
            public void onFailure(@NonNull Call<List<Category>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

}