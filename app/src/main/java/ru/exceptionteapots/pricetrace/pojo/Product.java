package ru.exceptionteapots.pricetrace.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * POJO-класс для продуктов
 * */
public class Product {
    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("img")
    @Expose
    private String img;

    @SerializedName("max_price")
    @Expose
    private int max_price;

    @SerializedName("min_price")
    @Expose
    private int min_price;

    @SerializedName("name")
    @Expose
    private String name;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getMax_price() {
        return max_price;
    }

    public void setMax_price(int max_price) {
        this.max_price = max_price;
    }

    public int getMin_price() {
        return min_price;
    }

    public void setMin_price(int min_price) {
        this.min_price = min_price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
