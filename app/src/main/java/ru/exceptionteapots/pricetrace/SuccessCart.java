package ru.exceptionteapots.pricetrace;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SuccessCart {
    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("reason")
    @Expose
    private String reason;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
