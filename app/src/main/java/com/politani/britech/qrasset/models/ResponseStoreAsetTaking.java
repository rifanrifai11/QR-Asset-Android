
package com.app.britech.riung.models;

import com.google.gson.annotations.SerializedName;

public class ResponseStoreAsetTaking {

    @SerializedName("data")
    private AsetTaking AsetTaking;
    @SerializedName("message")
    private String Message;
    @SerializedName("success")
    private Boolean Success;

    public AsetTaking getAsetTaking() {
        return AsetTaking;
    }

    public void setAsetTaking(AsetTaking asetTaking) {
        AsetTaking = asetTaking;
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
