
package com.politani.britech.qrasset.models;

import com.google.gson.annotations.SerializedName;

public class ResponseUser {

    @SerializedName("data")
    private User User;
    @SerializedName("message")
    private String Message;
    @SerializedName("success")
    private Boolean Success;

    public User getUser() {
        return User;
    }

    public void setUser(User user) {
        User = user;
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
