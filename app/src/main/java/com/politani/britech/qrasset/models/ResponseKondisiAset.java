
package com.app.britech.riung.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseKondisiAset {

    @SerializedName("data")
    private List<KondisiAset> KondisiAset;
    @SerializedName("message")
    private String Message;
    @SerializedName("success")
    private Boolean Success;

    public List<KondisiAset> getKondisiAset() {
        return KondisiAset;
    }

    public void setKondisiAset(List<KondisiAset> kondisiAset) {
        KondisiAset = kondisiAset;
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
