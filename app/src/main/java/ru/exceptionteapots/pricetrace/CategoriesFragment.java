package ru.exceptionteapots.pricetrace;

import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private List<ParentCategory> data = new ArrayList<>();
    private ListAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        SwipeRefreshLayout mSwipeRefreshLayout = view.findViewById(R.id.refresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.dark_red);
        mSwipeRefreshLayout.setOnRefreshListener(this);

//      mSwipeRefreshLayout.setRefreshing(true);
        RecyclerView recyclerView = view.findViewById(R.id.list);
        adapter = new ListAdapter(getContext(), data);
        recyclerView.setAdapter(adapter);
        return view;
    }
    @Override
    public void onRefresh() {
        Call<List<ParentCategory>> call = NetworkService.getInstance().getPriceTraceAPI().getAllParentCategories();
        call.enqueue(new Callback<List<ParentCategory>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<ParentCategory>> call, @NonNull Response<List<ParentCategory>> response) {
                        List<ParentCategory> list = response.body();
                        data.addAll(list);
//                        Toast.makeText(getActivity(), data.get(0).getName(), Toast.LENGTH_LONG).show();
//                        adapter.notifyDataSetChanged();
                        data = new ArrayList<>();
                    }
                    @Override
                    public void onFailure(@NonNull Call<List<ParentCategory>> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
    }