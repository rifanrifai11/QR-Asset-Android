
package com.app.britech.riung.models;

import com.google.gson.annotations.SerializedName;

public class UmurEkonomis {

    @SerializedName("created_at")
    private String CreatedAt;
    @SerializedName("deleted_at")
    private Object DeletedAt;
    @SerializedName("id")
    private Long Id;
    @SerializedName("nama")
    private String Nama;
    @SerializedName("nilai_rumus")
    private Double NilaiRumus;
    @SerializedName("tahun")
    private Long Tahun;
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

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public Double getNilaiRumus() {
        return NilaiRumus;
    }

    public void setNilaiRumus(Double nilaiRumus) {
        NilaiRumus = nilaiRumus;
    }

    public Long getTahun() {
        return Tahun;
    }

    public void setTahun(Long tahun) {
        Tahun = tahun;
    }

    public String getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        UpdatedAt = updatedAt;
    }

}
