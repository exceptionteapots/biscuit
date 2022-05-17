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
    private CategoryAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        mSwipeRefreshLayout = view.findViewById(R.id.categories_refresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.dark_red);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        RecyclerView recyclerView = view.findViewById(R.id.categories_list);
        adapter = new CategoryAdapter(getContext(), data, recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter( adapter );
        mSwipeRefreshLayout.setRefreshing(true);

        // отображение родительских категорий
        Call<List<Category>> call = NetworkService.getInstance().getPriceTraceAPI().getAllParentCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(@NonNull Call<List<Category>> call, @NonNull Response<List<Category>> response) {
                List<Category> list = response.body();
                if (list == null) {

                }
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

        return view;
    }


    /**
     * Функция отображения родительских категорий при обновлении экрана
     * **/
    @Override
    public void onRefresh() {
        // отображение родительских категорий
        Call<List<Category>> call = NetworkService.getInstance().getPriceTraceAPI().getAllParentCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(@NonNull Call<List<Category>> call, @NonNull Response<List<Category>> response) {
                List<Category> list = response.body();
                if (list == null) {

                }
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