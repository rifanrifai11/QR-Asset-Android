
package com.app.britech.riung.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.app.britech.riung.models.KondisiAset;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "aset_taking")
public class AsetTaking {

    @PrimaryKey
    @ColumnInfo(name="id")
    @NonNull
    private String Id;
    
    @ColumnInfo(name="data_aset_id")
    private Long DataAsetId;

    @ColumnInfo(name="kondisi_aset_id")
    private String KondisiAsetId;

    @ColumnInfo(name="updated_at")
    private String UpdatedAt;
    @ColumnInfo(name="users_id")
    private Long UsersId;

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
}
