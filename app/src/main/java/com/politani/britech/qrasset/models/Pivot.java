
package com.app.britech.riung.models;

import com.google.gson.annotations.SerializedName;

public class Pivot {

    @SerializedName("role_id")
    private Long RoleId;
    @SerializedName("user_id")
    private Long UserId;

    public Long getRoleId() {
        return RoleId;
    }

    public void setRoleId(Long roleId) {
        RoleId = roleId;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

}
