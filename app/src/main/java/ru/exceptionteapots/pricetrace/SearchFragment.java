package ru.exceptionteapots.pricetrace;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {
    private List<Product> data = new ArrayList<>();
    private ProductAdapter adapter;
    private TextInputEditText searchField;
    private TextView warning;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        new Thread() {
            @Override
            public void run() {
            RecyclerView recyclerView = view.findViewById(R.id.search_list);
            adapter = new ProductAdapter(getContext(), data, recyclerView);
            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(llm);
            recyclerView.setAdapter(adapter);

            searchField = view.findViewById(R.id.search_text_field);
            warning = view.findViewById(R.id.textView2);
            adapter.notifyDataSetChanged();
            searchField.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if (searchField.getText().toString().isEmpty()) {
                        data.clear();
                        adapter.notifyDataSetChanged();
                        warning.setText(getString(R.string.search_success));
                    } else {
                        Call<List<Product>> call = NetworkService.getInstance().getPriceTraceAPI().getProductsBySearch(searchField.getText().toString());
                        call.enqueue(new Callback<List<Product>>() {
                            @Override
                            public void onResponse(@NonNull Call<List<Product>> call, @NonNull Response<List<Product>> response) {
                                List<Product> list = response.body();
                                if (list != null) {
                                    data.clear();
                                    data.addAll(list);
                                    adapter.notifyDataSetChanged();
                                    warning.setText(getString(R.string.search_success));
                                }
                                else {
                                    data.clear();
                                    adapter.notifyDataSetChanged();
                                    warning.setText(getString(R.string.search_error));
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<List<Product>> call, @NonNull Throwable t) {
                                t.printStackTrace();
                            }
                        });
                    }
                }
            });
        }
        }.start();
        return view;
    }



}