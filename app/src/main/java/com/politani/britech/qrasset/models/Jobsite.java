
package com.app.britech.riung.models;

import com.google.gson.annotations.SerializedName;

public class Jobsite {

    @SerializedName("created_at")
    private String CreatedAt;
    @SerializedName("deleted_at")
    private Object DeletedAt;
    @SerializedName("id")
    private Long Id;
    @SerializedName("keterangan")
    private Object Keterangan;
    @SerializedName("nama")
    private String Nama;
    @SerializedName("updated_at")
    private String UpdatedAt;

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        CreatedAt = createdAt;
    }

    public Object getDeletedAt() {
        return DeletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        DeletedAt = deletedAt;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Object getKeterangan() {
        return Keterangan;
    }

    public void setKeterangan(Object keterangan) {
        Keterangan = keterangan;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        UpdatedAt = updatedAt;
    }

}
