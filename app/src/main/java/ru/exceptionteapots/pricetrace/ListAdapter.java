package ru.exceptionteapots.pricetrace;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> implements View.OnClickListener {

    private final LayoutInflater inflater;
    private final List<Category> items;
    private final View.OnClickListener mOnClickListener = this;
    private boolean parent = true;

    ListAdapter(Context context, List<Category> items) {
        this.items = items;
        this.inflater = LayoutInflater.from(context);
    }
    ListAdapter(Context context, List<Category> items, boolean parent) {
        this.items = items;
        this.inflater = LayoutInflater.from(context);
        this.parent = parent;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.category_item, parent, false);
        view.setOnClickListener(mOnClickListener);
        return new ViewHolder(view);
    }

    /**
     * Заполнение .xml-шаблона в соответствии с полученными даннами
     * */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category item = items.get(position);
        holder.nameView.setText(item.getName());
        holder.idView.setText(String.valueOf(item.getId()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * Клик по элементу списка. Два сценария:
     * <ol>
     *     <li>клик по родительскому => отображение дочерних</li>
     *     <li>клик по дочерней => отображение товаров</li>
     * </ol>
     * */
    @Override
    public void onClick(View view) {
        TextView category_id = view.findViewById(R.id.category_id);
        Bundle arg = new Bundle();
        // клик по родительскому
        if (this.parent) {
            arg.putInt("Parent", Integer.parseInt(category_id.getText().toString()));
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_categoriesFragment_self, arg);
        }
        // клик по дочерней
        else {
            Toast.makeText(this.inflater.getContext(), category_id.getText(), Toast.LENGTH_SHORT).show();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView, idView;
        ViewHolder(View view){
            super(view);
            nameView = view.findViewById(R.id.category_name);
            idView = view.findViewById(R.id.category_id);
        }
    }
}
