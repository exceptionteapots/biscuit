package ru.exceptionteapots.pricetrace;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        View view = inflater.inflate(R.layout.fragment_list_product, container, false);
        mSwipeRefreshLayout = view.findViewById(R.id.list_product_refresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.dark_red);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        RecyclerView recyclerView = view.findViewById(R.id.list_product_list);

        categoryID = ListProductFragmentArgs.fromBundle(getArguments()).getCategoryID();

        adapter = new ProductAdapter(getContext(), data, recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter( adapter );
        mSwipeRefreshLayout.setRefreshing(true);

        Call<List<Product>> call = NetworkService.getInstance().getPriceTraceAPI().getProductsByCategory(categoryID);
        call.enqueue(new Callback<List<Product>>() {
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
                            .setTitle(getString(R.string.network_error_title))
                            .setMessage(getString(R.string.network_error_message))
                            .setIcon(R.drawable.ic_cancel)
                            .setPositiveButton(getString(R.string.ok), (dialogInterface, i) -> {

                            })
                            .show();
//                    getParentFragmentManager().popBackStack();
                }
            }
            @Override
            public void onFailure(@NonNull Call<List<Product>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });

        return view;
    }

    /**
     * ?????????????? ?????????????????????? ?????????????? ?????? ???????????????????? ????????????
     * */
    @Override
    public void onRefresh() {
        Call<List<Product>> call = NetworkService.getInstance().getPriceTraceAPI().getProductsByCategory(categoryID);
        call.enqueue(new Callback<List<Product>>() {
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
                            .setTitle(getString(R.string.network_error_title))
                            .setMessage(getString(R.string.network_error_message))
                            .setIcon(R.drawable.ic_cancel)
                            .setPositiveButton(getString(R.string.ok), (dialogInterface, i) -> {

                            })
                            .show();
//                    getParentFragmentManager().popBackStack();
                }
            }
            @Override
            public void onFailure(@NonNull Call<List<Product>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

}