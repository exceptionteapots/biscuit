package ru.exceptionteapots.pricetrace;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<Product> items;
    private final RecyclerView recyclerView;

    ProductAdapter(Context context, List<Product> items, RecyclerView recyclerView) {
        this.items = items;
        this.inflater = LayoutInflater.from(context);
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.product_item, parent, false);
        // подробный просмотр товара при нажатии на всю карточку
        view.setOnClickListener(onProductClick);
        // добавление в корзину при нажатии
        Button toCart = view.findViewById(R.id.product_button);
        toCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int itemPosition = recyclerView.getChildLayoutPosition((View) view.getParent());
                int product_id = items.get(itemPosition).getId();
                Toast.makeText(inflater.getContext(), "to cart" + product_id, Toast.LENGTH_SHORT).show();
            }
        });
        return new ViewHolder(view);
    }

    /**
     * Заполнение .xml-шаблона в соответствии с полученными даннами
     * */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product item = items.get(position);

                holder.productName.setText(item.getName());
                // срез описания товара до 100 символов + проверка по длине
                String description = item.getDescription();
                if (description != null)
                    holder.productDescription.setText(description.length() > 100 ? description.substring(0, 100) : description + "...");
                if (item.getMin_price() != 0) {
                String price = "от " + item.getMin_price() + "₽ до " + item.getMax_price() + "₽";
                holder.productPrice.setText(price);}


        try {
            Picasso.get().load("https://pricetrace.ru/static/img/" + item.getImg()).into(holder.productImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private final View.OnClickListener onProductClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int itemPosition = recyclerView.getChildLayoutPosition(view);
            int product_id = items.get(itemPosition).getId();
            Toast.makeText(inflater.getContext(), "product clicked" + product_id, Toast.LENGTH_SHORT).show();
        }
    };


    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView productImage;
        final TextView productName, productDescription, productPrice;
        ViewHolder(View view){
            super(view);
            productImage = view.findViewById(R.id.product_img);
            productName = view.findViewById(R.id.product_name);
            productDescription = view.findViewById(R.id.product_description);
            productPrice = view.findViewById(R.id.product_price);
        }
    }
}
