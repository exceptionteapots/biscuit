package ru.exceptionteapots.pricetrace.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * POJO-класс для дочерних и родительских категорий
 * */
public class Category {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("img")
    @Expose
    private String img;

    @SerializedName("name")
    @Expose
    private String name;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
