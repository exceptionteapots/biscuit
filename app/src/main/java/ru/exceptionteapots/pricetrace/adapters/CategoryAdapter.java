package ru.exceptionteapots.pricetrace.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.exceptionteapots.pricetrace.pojo.Category;
import ru.exceptionteapots.pricetrace.R;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> implements View.OnClickListener {

    private final LayoutInflater inflater;
    private final List<Category> items;
    private final RecyclerView recyclerView;
    private final boolean isParent;

    public CategoryAdapter(Context context, List<Category> items, RecyclerView recyclerView, boolean isParent) {
        this.items = items;
        this.inflater = LayoutInflater.from(context);
        this.recyclerView = recyclerView;
        this.isParent = isParent;
    }
    public CategoryAdapter(Context context, List<Category> items, RecyclerView recyclerView) {
        this.items = items;
        this.inflater = LayoutInflater.from(context);
        this.recyclerView = recyclerView;
        this.isParent = true;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.category_item, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    /**
     * Заполнение .xml-шаблона в соответствии с полученными даннами
     * */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category item = items.get(position);
        holder.nameView.setText(item.getName());
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
        int itemPosition = recyclerView.getChildLayoutPosition(view);
        int category_id = items.get(itemPosition).getId();
        Bundle arg = new Bundle();
        arg.putInt("Category_ID", category_id);
        NavController navController = Navigation.findNavController(view);
        if (isParent) navController.navigate(R.id.subcategoriesFragment, arg);
        else navController.navigate(R.id.listProductFragment, arg);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView;
        ViewHolder(View view){
            super(view);
            nameView = view.findViewById(R.id.category_name);
        }
    }
}
