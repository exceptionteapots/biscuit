package ru.exceptionteapots.pricetrace;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<ParentCategory> items;

    ListAdapter(Context context, List<ParentCategory> items) {
        this.items = items;
        this.inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.category_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ParentCategory item = items.get(position);
        holder.nameView.setText(item.getName());
        holder.idView.setText(item.getId());
    }

    @Override
    public int getItemCount() {
        return items.size();
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
