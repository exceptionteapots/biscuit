package ru.exceptionteapots.pricetrace;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface PriceTraceAPI {
    @GET("/categories")
    public Call<List<ParentCategory>> getAllParentCategories();
}
