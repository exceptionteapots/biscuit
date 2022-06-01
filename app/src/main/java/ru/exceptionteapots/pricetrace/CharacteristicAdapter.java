package ru.exceptionteapots.pricetrace;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CharacteristicAdapter extends RecyclerView.Adapter<CharacteristicAdapter.ViewHolder> implements View.OnClickListener {

    private final LayoutInflater inflater;
    private final List<Characteristic> items;

    CharacteristicAdapter(Context context, List<Characteristic> items) {
        this.items = items;
        this.inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.characteristic_item, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    /**
     * Заполнение .xml-шаблона в соответствии с полученными даннами
     * */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Characteristic item = items.get(position);
        holder.key.setText(item.getName());
        holder.value.setText(item.getValue());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onClick(View view) {
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView key, value;
        ViewHolder(View view){
            super(view);
            key = view.findViewById(R.id.key);
            value = view.findViewById(R.id.value);
        }
    }
}
