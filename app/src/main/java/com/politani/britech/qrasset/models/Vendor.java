
package com.app.britech.riung.models;

import com.google.gson.annotations.SerializedName;

public class Vendor {

    @SerializedName("alamat")
    private String Alamat;
    @SerializedName("attn")
    private String Attn;
    @SerializedName("created_at")
    private String CreatedAt;
    @SerializedName("deleted_at")
    private Object DeletedAt;
    @SerializedName("email")
    private String Email;
    @SerializedName("fax")
    private String Fax;
    @SerializedName("id")
    private Long Id;
    @SerializedName("kode_registrasi")
    private String KodeRegistrasi;
    @SerializedName("kota")
    private String Kota;
    @SerializedName("nama")
    private String Nama;
    @SerializedName("phone")
    private String Phone;
    @SerializedName("telepon")
    private String Telepon;
    @SerializedName("updated_at")
    private String UpdatedAt;

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String alamat) {
        Alamat = alamat;
    }

    public String getAttn() {
        return Attn;
    }

    public void setAttn(String attn) {
        Attn = attn;
    }

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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String fax) {
        Fax = fax;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getKodeRegistrasi() {
        return KodeRegistrasi;
    }

    public void setKodeRegistrasi(String kodeRegistrasi) {
        KodeRegistrasi = kodeRegistrasi;
    }

    public String getKota() {
        return Kota;
    }

    public void setKota(String kota) {
        Kota = kota;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getTelepon() {
        return Telepon;
    }

    public void setTelepon(String telepon) {
        Telepon = telepon;
    }

    public String getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        UpdatedAt = updatedAt;
    }

}
