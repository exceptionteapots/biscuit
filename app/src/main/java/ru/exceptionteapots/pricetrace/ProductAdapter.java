package ru.exceptionteapots.pricetrace;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<Product> items;

    ProductAdapter(Context context, List<Product> items) {
        this.items = items;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.product_item, parent, false);
        // подробный просмотр товара при нажатии на всю карточку
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(inflater.getContext(), "product clicked", Toast.LENGTH_SHORT).show();
            }
        });
        // добавление в корзину при нажатии
        Button toCart = view.findViewById(R.id.product_button);
        toCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(inflater.getContext(), "to cart", Toast.LENGTH_SHORT).show();
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
        Thread thread = new Thread(){
            @Override
            public void run() {

                holder.productName.setText(item.getName());
                holder.productId.setText(String.valueOf(item.getId()));

                // срез описания товара до 100 символов + проверка по длине
                String description = item.getDescription();
                holder.productDescription.setText(description.length() > 100 ? description.substring(0, 100) : description + "...");

                String price = "от " + item.getMin_price() + "₽ до " + item.getMax_price() + "₽";
                holder.productPrice.setText(price);

                try {
                    InputStream is = (InputStream) new URL("https://pricetrace.ru/static/img/" + item.getImg()).getContent();
                    Drawable img = Drawable.createFromStream(is, null);
                    holder.productImage.setImageDrawable(img);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView productImage;
        final TextView productName, productId, productDescription, productPrice;
        ViewHolder(View view){
            super(view);
            productImage = view.findViewById(R.id.product_img);
            productName = view.findViewById(R.id.product_name);
            productId = view.findViewById(R.id.product_id);
            productDescription = view.findViewById(R.id.product_description);
            productPrice = view.findViewById(R.id.product_price);
        }
    }
}
