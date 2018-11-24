
package com.app.britech.riung.models;

import com.google.gson.annotations.SerializedName;

public class AsetTaking {

    @SerializedName("created_at")
    private String CreatedAt;
    @SerializedName("data_aset_id")
    private Long DataAsetId;
    @SerializedName("id")
    private String Id;
    @SerializedName("kondisi_aset_id")
    private String KondisiAsetId;

    @SerializedName("kondisi_aset")
    private KondisiAset KondisiAset;

    @SerializedName("updated_at")
    private String UpdatedAt;
    @SerializedName("users_id")
    private Long UsersId;

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        CreatedAt = createdAt;
    }

    public Long getDataAsetId() {
        return DataAsetId;
    }

    public void setDataAsetId(Long dataAsetId) {
        DataAsetId = dataAsetId;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getKondisiAsetId() {
        return KondisiAsetId;
    }

    public void setKondisiAsetId(String kondisiAsetId) {
        KondisiAsetId = kondisiAsetId;
    }

    public String getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        UpdatedAt = updatedAt;
    }

    public Long getUsersId() {
        return UsersId;
    }

    public void setUsersId(Long usersId) {
        UsersId = usersId;
    }

    public com.app.britech.riung.models.KondisiAset getKondisiAset() {
        return KondisiAset;
    }

    public void setKondisiAset(com.app.britech.riung.models.KondisiAset kondisiAset) {
        KondisiAset = kondisiAset;
    }
}
