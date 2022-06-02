package ru.exceptionteapots.pricetrace;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * POJO-класс для продуктов
 * */
public class FullProduct {
    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("product_id")
    @Expose
    private int product_id;

    @SerializedName("img")
    @Expose
    private String img;

    @SerializedName("category_id")
    @Expose
    private int category_id;

    @SerializedName("name")
    @Expose
    private String name;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
