package ru.exceptionteapots.pricetrace;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface PriceTraceAPI {
    @GET("/categories")
    public Call<List<Category>> getAllParentCategories();

    @GET("/categories/{parent_id}/subcategories")
    public Call<List<Category>> getSubcategoryByParentId(@Path("parent_id") int parent_id);

    @GET("/products/by_category/{category_id}")
    public Call<List<Product>> getProductsByCategory(@Path("category_id") int category_id);

    @GET("/products/{product_id}/characteristics")
    public Call<List<Characteristic>> getCharacteristicsByProductId(@Path("product_id") int product_id);

    @GET("/search")
    public Call<List<Product>> getProductsBySearch(@Query("query") String query);

    @GET("/user")
    public Call<User> isAuthenticated(@Header("Authorization") String token);
}
