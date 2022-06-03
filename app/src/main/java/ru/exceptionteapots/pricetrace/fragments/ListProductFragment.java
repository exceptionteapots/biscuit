package ru.exceptionteapots.pricetrace.fragments;

import static ru.exceptionteapots.pricetrace.NetworkService.hasConnection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

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
import ru.exceptionteapots.pricetrace.MainActivity;
import ru.exceptionteapots.pricetrace.NetworkService;
import ru.exceptionteapots.pricetrace.pojo.Product;
import ru.exceptionteapots.pricetrace.adapters.ProductAdapter;
import ru.exceptionteapots.pricetrace.R;

public class ListProductFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private List<Product> data = new ArrayList<>();
    private ProductAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private int categoryID;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity) requireActivity()).setChosenFragment(0);
        View view = inflater.inflate(R.layout.fragment_list_product, container, false);
        mSwipeRefreshLayout = view.findViewById(R.id.list_product_refresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.dark_red);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        RecyclerView recyclerView = view.findViewById(R.id.list_product_list);

        categoryID = ListProductFragmentArgs.fromBundle(getArguments()).getCategoryID();
        FrameLayout frameLayout = view.findViewById(R.id.bottom_sheet);
        adapter = new ProductAdapter(getContext(), data, recyclerView, frameLayout);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter( adapter );
        mSwipeRefreshLayout.setRefreshing(true);

        onRefresh();


        return view;
    }

    /**
     * Функция отображения товаров при обновлении экрана
     * */
    @Override
    public void onRefresh() {
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
        NetworkService.getInstance().getPriceTraceAPI().getProductsByCategory(categoryID).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(@NonNull Call<List<Product>> call, @NonNull Response<List<Product>> response) {
                List<Product> list = response.body();
                if (list != null) {
                    data.addAll(list);

                    data = new ArrayList<>();
                    mSwipeRefreshLayout.setRefreshing(false);
                    adapter.notifyDataSetChanged();
                }
                else {
                    new MaterialAlertDialogBuilder(getContext())
                            .setTitle(getString(R.string.server_error_title))
                            .setMessage(getString(R.string.server_error_message))
                            .setIcon(R.drawable.ic_cancel)
                            .setPositiveButton(getString(R.string.ok), (dialogInterface, i) -> {

                            })
                            .show();
                    getParentFragmentManager().popBackStack();
                }
            }
            @Override
            public void onFailure(@NonNull Call<List<Product>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

}