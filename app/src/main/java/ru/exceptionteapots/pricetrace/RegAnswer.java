package ru.exceptionteapots.pricetrace;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegAnswer {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("reason")
    @Expose
    private String reason;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
