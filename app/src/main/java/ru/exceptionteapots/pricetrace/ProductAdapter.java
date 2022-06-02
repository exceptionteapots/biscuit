package ru.exceptionteapots.pricetrace;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.divider.MaterialDividerItemDecoration;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<Product> items;
    private final RecyclerView recyclerView;
    private final BottomSheetBehavior sheetBehavior;
    private final TextView sheetName;
    private final TextView sheetDescription;
    private final TextView sheetDescriptionTitle;
    private final TextView sheetCharacteristicsTitle;
    private final ImageView sheetImage;
    private final ArrayList<Characteristic> characteristics;
    private final CharacteristicAdapter characteristicAdapter;
    private final NestedScrollView nestedScrollView;
    private final RecyclerView sheetCharacteristics;
    private final SharedPreferences sharedPreferences;

    ProductAdapter(Context context, List<Product> items, RecyclerView recyclerView, FrameLayout frameLayout) {
        this.items = items;
        this.inflater = LayoutInflater.from(context);
        this.recyclerView = recyclerView;
        this.sheetBehavior = BottomSheetBehavior.from(frameLayout);
        this.sheetName = frameLayout.findViewById(R.id.sheet_title);
        this.sheetDescription = frameLayout.findViewById(R.id.sheet_description);
        this.sheetImage = frameLayout.findViewById(R.id.sheet_img);
        this.sheetDescriptionTitle = frameLayout.findViewById(R.id.sheet_description_title);
        this.sheetCharacteristicsTitle = frameLayout.findViewById(R.id.sheet_characteristics_title);
        this.nestedScrollView = frameLayout.findViewById(R.id.nested);

        this.sheetCharacteristics = frameLayout.findViewById(R.id.sheet_characteristics);
        this.characteristics = new ArrayList<>();
        this.characteristicAdapter = new CharacteristicAdapter(context, characteristics);

        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        this.sheetCharacteristics.setLayoutManager(llm);
        this.sheetCharacteristics.setAdapter(characteristicAdapter);

        MaterialDividerItemDecoration devider = new MaterialDividerItemDecoration(context, LinearLayoutManager.VERTICAL);
        devider.setDividerColor(context.getColor(R.color.dark_red));
        this.sheetCharacteristics.addItemDecoration(devider);

        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.product_item, parent, false);
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
                if (description != null && description.length() > 0)
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final ImageView productImage;
        final TextView productName, productDescription, productPrice;
        final Button toCart;
        ViewHolder(View view){
            super(view);
            productImage = view.findViewById(R.id.product_img);
            productName = view.findViewById(R.id.product_name);
            productDescription = view.findViewById(R.id.product_description);
            productPrice = view.findViewById(R.id.product_price);
            toCart = view.findViewById(R.id.product_button);
            view.setOnClickListener(this);
            toCart.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int itemPosition = getAdapterPosition();
            int productID = items.get(itemPosition).getId();
            if (view.getId() == toCart.getId()) {
                String token = sharedPreferences.getString("token", "");
                if (token.isEmpty()) {
                    Snackbar.make(view, "Вы не авторизованы", Snackbar.LENGTH_LONG).show();
                    return;
                }
                Cart cart = new Cart();
                cart.setProductID(productID);
                cart.setCount(1);
                Call<Void> call = NetworkService.getInstance().getPriceTraceAPI().addToCart(cart, "Bearer " + token);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                        if (response.isSuccessful()) {
                            Snackbar.make(view, "Товар добавлен в коризну", Snackbar.LENGTH_LONG).show();
                        }
                        else {
                            Snackbar.make(view, "Вы не авторизованы", Snackbar.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                        Snackbar.make(view, "Вы не авторизованы", Snackbar.LENGTH_LONG).show();
                    }
                });
            }
            else {
                if (sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) return;
                sheetCharacteristicsTitle.setVisibility(View.GONE);
                sheetCharacteristics.setVisibility(View.GONE);
                nestedScrollView.scrollTo(0,0);
                sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                String productName = items.get(itemPosition).getName();
                String productDescription = items.get(itemPosition).getDescription();
                if (productDescription != null) {
                    if (productDescription.isEmpty()) sheetDescriptionTitle.setVisibility(View.GONE);
                    else sheetDescriptionTitle.setVisibility(View.VISIBLE);
                }
                else sheetDescriptionTitle.setVisibility(View.GONE);
                String productImage = items.get(itemPosition).getImg();

                sheetName.setText(productName);
                sheetDescription.setText(productDescription);
                try {
                    Picasso.get().load("https://pricetrace.ru/static/img/" + productImage).into(sheetImage);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Call<List<Characteristic>> call = NetworkService.getInstance().getPriceTraceAPI().getCharacteristicsByProductId(productID);
                call.enqueue(new Callback<List<Characteristic>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Characteristic>> call, @NonNull Response<List<Characteristic>> response) {
                        List<Characteristic> list = response.body();
                        characteristics.clear();
                        if (list.get(0).getId() != 0) {
                            characteristics.addAll(list);
                            sheetCharacteristicsTitle.setVisibility(View.VISIBLE);
                            sheetCharacteristics.setVisibility(View.VISIBLE);
                        }
                        characteristicAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onFailure(@NonNull Call<List<Characteristic>> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        }
    }
}
