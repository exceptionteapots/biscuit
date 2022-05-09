package ru.exceptionteapots.pricetrace;

import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private List<Category> data = new ArrayList<>();
    private ListAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private int parent_id;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        mSwipeRefreshLayout = view.findViewById(R.id.refresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.dark_red);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        RecyclerView recyclerView = view.findViewById(R.id.list);
        parent_id = CategoriesFragmentArgs.fromBundle(getArguments()).getParent();

        if (parent_id == 0) adapter = new ListAdapter(getContext(), data);
        else adapter = new ListAdapter(getContext(), data, false);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter( adapter );
        mSwipeRefreshLayout.setRefreshing(true);
        onRefresh();
        return view;
    }


    /**
     * Функция, обновляющая список по двум сценариям:
     * <ol>
     *     <li>отображение родительских категорий
     *     <li>отображение дочерних категорий
     * </ol>
     * **/
    @Override
    public void onRefresh() {
        // отображение родительских категорий
        if (parent_id == 0) {
            Call<List<Category>> call = NetworkService.getInstance().getPriceTraceAPI().getAllParentCategories();
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
        // отображение дочерних категорий
        else {
            Call<List<Category>> call = NetworkService.getInstance().getPriceTraceAPI().getSubcategoryByParentId(parent_id);
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
}