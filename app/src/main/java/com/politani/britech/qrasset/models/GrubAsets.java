
package com.app.britech.riung.models;

import com.google.gson.annotations.SerializedName;

public class GrubAsets {

    @SerializedName("created_at")
    private String CreatedAt;
    @SerializedName("deleted_at")
    private Object DeletedAt;
    @SerializedName("kategori_aset_id")
    private Long KategoriAsetId;
    @SerializedName("keterangan")
    private Object Keterangan;
    @SerializedName("kode")
    private String Kode;
    @SerializedName("nama")
    private String Nama;
    @SerializedName("parent_grub_aset_kode")
    private String ParentGrubAsetKode;
    @SerializedName("umur_ekonomis")
    private com.app.britech.riung.models.UmurEkonomis UmurEkonomis;
    @SerializedName("umur_ekonomis_id")
    private Long UmurEkonomisId;
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

    public Long getKategoriAsetId() {
        return KategoriAsetId;
    }

    public void setKategoriAsetId(Long kategoriAsetId) {
        KategoriAsetId = kategoriAsetId;
    }

    public Object getKeterangan() {
        return Keterangan;
    }

    public void setKeterangan(Object keterangan) {
        Keterangan = keterangan;
    }

    public String getKode() {
        return Kode;
    }

    public void setKode(String kode) {
        Kode = kode;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getParentGrubAsetKode() {
        return ParentGrubAsetKode;
    }

    public void setParentGrubAsetKode(String parentGrubAsetKode) {
        ParentGrubAsetKode = parentGrubAsetKode;
    }

    public com.app.britech.riung.models.UmurEkonomis getUmurEkonomis() {
        return UmurEkonomis;
    }

    public void setUmurEkonomis(com.app.britech.riung.models.UmurEkonomis umurEkonomis) {
        UmurEkonomis = umurEkonomis;
    }

    public Long getUmurEkonomisId() {
        return UmurEkonomisId;
    }

    public void setUmurEkonomisId(Long umurEkonomisId) {
        UmurEkonomisId = umurEkonomisId;
    }

    public String getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        UpdatedAt = updatedAt;
    }

}
