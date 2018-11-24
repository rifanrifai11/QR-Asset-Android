
package com.app.britech.riung.models;

import com.google.gson.annotations.SerializedName;

public class ResponseDataAset {

    @SerializedName("data")
    private DataAset DataAset;
    @SerializedName("message")
    private String Message;
    @SerializedName("success")
    private Boolean Success;

    public DataAset getDataAset() {
        return DataAset;
    }

    public void setDataAset(DataAset dataAset) {
        DataAset = dataAset;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Boolean getSuccess() {
        return Success;
    }

    public void setSuccess(Boolean success) {
        Success = success;
    }

}
