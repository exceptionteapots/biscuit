package ru.exceptionteapots.pricetrace.fragments;

import static ru.exceptionteapots.pricetrace.NetworkService.hasConnection;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.exceptionteapots.pricetrace.pojo.Cart;
import ru.exceptionteapots.pricetrace.pojo.FullProduct;
import ru.exceptionteapots.pricetrace.MainActivity;
import ru.exceptionteapots.pricetrace.NetworkService;
import ru.exceptionteapots.pricetrace.adapters.ProductAdapterCart;
import ru.exceptionteapots.pricetrace.R;

public class CartFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private List<FullProduct> data = new ArrayList<>();
    private ProductAdapterCart adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity) requireActivity()).setChosenFragment(2);
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        mSwipeRefreshLayout = view.findViewById(R.id.cart_refresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.dark_red);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        RecyclerView recyclerView = view.findViewById(R.id.cart_list);

        FrameLayout frameLayout = view.findViewById(R.id.bottom_sheet);
        adapter = new ProductAdapterCart(getContext(), data, recyclerView, frameLayout);
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
            return;
        }

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String token = sharedPreferences.getString("token", "");
        Call<List<Cart>> call = NetworkService.getInstance().getPriceTraceAPI().getCart("Bearer " + token);
        call.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(@NonNull Call<List<Cart>> call, @NonNull Response<List<Cart>> response) {
                data.clear();
                List<Cart> list = response.body();
                if (list != null) {

                    for (Cart cart: list) {
                        NetworkService.getInstance().getPriceTraceAPI().getProductById(cart.getProductID()).enqueue(new Callback<FullProduct>() {
                            @Override
                            public void onResponse(@NonNull Call<FullProduct> call, @NonNull Response<FullProduct> response) {
                                data.add(response.body());
                                adapter.notifyDataSetChanged();
                            }
                            @Override
                            public void onFailure(@NonNull Call<FullProduct> call, @NonNull Throwable t) {
                                t.printStackTrace();
                            }
                        });
                    }

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

                mSwipeRefreshLayout.setRefreshing(false);
            }
            @Override
            public void onFailure(@NonNull Call<List<Cart>> call, @NonNull Throwable t) {
                t.printStackTrace();
                new MaterialAlertDialogBuilder(getContext())
                        .setTitle(getString(R.string.cart_empty_title))
                        .setMessage(getString(R.string.cart_empty_message))
                        .setIcon(R.drawable.ic_add_to_cart)
                        .setPositiveButton(getString(R.string.ok), (dialogInterface, i) -> {})
                        .show();
                getParentFragmentManager().popBackStack();
            }
        });

    }
}