
package com.app.britech.riung.models;

import com.google.gson.annotations.SerializedName;

public class AsetPembelian {

    @SerializedName("check")
    private Object Check;
    @SerializedName("created_at")
    private String CreatedAt;
    @SerializedName("data_aset_id")
    private Long DataAsetId;
    @SerializedName("deleted_at")
    private Object DeletedAt;
    @SerializedName("harga_barang")
    private Long HargaBarang;
    @SerializedName("id")
    private Long Id;
    @SerializedName("jabatan_check")
    private Object JabatanCheck;
    @SerializedName("jabatan_mengetahui1")
    private Object JabatanMengetahui1;
    @SerializedName("jabatan_mengetahui2")
    private Object JabatanMengetahui2;
    @SerializedName("jabatan_raised_by")
    private Object JabatanRaisedBy;
    @SerializedName("mengetahui1")
    private Object Mengetahui1;
    @SerializedName("mengetahui2")
    private Object Mengetahui2;
    @SerializedName("nomor_general_request")
    private Object NomorGeneralRequest;
    @SerializedName("nomor_purchase_order")
    private Object NomorPurchaseOrder;
    @SerializedName("nomor_surat")
    private Object NomorSurat;
    @SerializedName("raised_by")
    private Object RaisedBy;
    @SerializedName("tanggal_pembelian")
    private String TanggalPembelian;
    @SerializedName("updated_at")
    private String UpdatedAt;
    @SerializedName("users_id")
    private Long UsersId;

    public Object getCheck() {
        return Check;
    }

    public void setCheck(Object check) {
        Check = check;
    }

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

    public Object getDeletedAt() {
        return DeletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        DeletedAt = deletedAt;
    }

    public Long getHargaBarang() {
        return HargaBarang;
    }

    public void setHargaBarang(Long hargaBarang) {
        HargaBarang = hargaBarang;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Object getJabatanCheck() {
        return JabatanCheck;
    }

    public void setJabatanCheck(Object jabatanCheck) {
        JabatanCheck = jabatanCheck;
    }

    public Object getJabatanMengetahui1() {
        return JabatanMengetahui1;
    }

    public void setJabatanMengetahui1(Object jabatanMengetahui1) {
        JabatanMengetahui1 = jabatanMengetahui1;
    }

    public Object getJabatanMengetahui2() {
        return JabatanMengetahui2;
    }

    public void setJabatanMengetahui2(Object jabatanMengetahui2) {
        JabatanMengetahui2 = jabatanMengetahui2;
    }

    public Object getJabatanRaisedBy() {
        return JabatanRaisedBy;
    }

    public void setJabatanRaisedBy(Object jabatanRaisedBy) {
        JabatanRaisedBy = jabatanRaisedBy;
    }

    public Object getMengetahui1() {
        return Mengetahui1;
    }

    public void setMengetahui1(Object mengetahui1) {
        Mengetahui1 = mengetahui1;
    }

    public Object getMengetahui2() {
        return Mengetahui2;
    }

    public void setMengetahui2(Object mengetahui2) {
        Mengetahui2 = mengetahui2;
    }

    public Object getNomorGeneralRequest() {
        return NomorGeneralRequest;
    }

    public void setNomorGeneralRequest(Object nomorGeneralRequest) {
        NomorGeneralRequest = nomorGeneralRequest;
    }

    public Object getNomorPurchaseOrder() {
        return NomorPurchaseOrder;
    }

    public void setNomorPurchaseOrder(Object nomorPurchaseOrder) {
        NomorPurchaseOrder = nomorPurchaseOrder;
    }

    public Object getNomorSurat() {
        return NomorSurat;
    }

    public void setNomorSurat(Object nomorSurat) {
        NomorSurat = nomorSurat;
    }

    public Object getRaisedBy() {
        return RaisedBy;
    }

    public void setRaisedBy(Object raisedBy) {
        RaisedBy = raisedBy;
    }

    public String getTanggalPembelian() {
        return TanggalPembelian;
    }

    public void setTanggalPembelian(String tanggalPembelian) {
        TanggalPembelian = tanggalPembelian;
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
