
package com.app.britech.riung.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.Nullable;

@Entity(tableName = "data_aset")
public class DataAset {

    @PrimaryKey
    @ColumnInfo(name="id")
    @Nullable
    private Long Id;

    @ColumnInfo(name="created_at")
    private String CreatedAt;
    @ColumnInfo(name="deleted_at")
    private String DeletedAt;
    @ColumnInfo(name="departemen_id")
    private Long DepartemenId;
    @ColumnInfo(name="grub_aset_kode")
    private String GrubAsetKode;
    @ColumnInfo(name="harga_sekarang_bulan")
    private Long HargaSekarangBulan;
    @ColumnInfo(name="harga_sekarang_tahun")
    private Long HargaSekarangTahun;

    @ColumnInfo(name="jobsite_id")
    private Long JobsiteId;
    @ColumnInfo(name="kode_data_aset")
    private String KodeDataAset;
    @ColumnInfo(name="lokasi")
    private String Lokasi;
    @ColumnInfo(name="masa_pakai_bulan")
    private Long MasaPakaiBulan;
    @ColumnInfo(name="masa_pakai_tahun")
    private Long MasaPakaiTahun;
    @ColumnInfo(name="merek_id")
    private Long MerekId;
    @ColumnInfo(name="nilai_sisa")
    private Long NilaiSisa;
    @ColumnInfo(name="no_registrasi")
    private String NoRegistrasi;
    @ColumnInfo(name="penyusutan_per_bulan")
    private Long PenyusutanPerBulan;
    @ColumnInfo(name="penyusutan_per_tahun")
    private Long PenyusutanPerTahun;
    @ColumnInfo(name="serial_number")
    private String SerialNumber;
    @ColumnInfo(name="spesifikasi")
    private String Spesifikasi;
    @ColumnInfo(name="tanggal_registrasi")
    private String TanggalRegistrasi;
    @ColumnInfo(name="tipe_id")
    private Long TipeId;
    @ColumnInfo(name="updated_at")
    private String UpdatedAt;
    @ColumnInfo(name="urut")
    private Long Urut;
    @ColumnInfo(name="vendor_id")
    private Long VendorId;

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        CreatedAt = createdAt;
    }

    public String getDeletedAt() {
        return DeletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        DeletedAt = deletedAt;
    }

    public Long getDepartemenId() {
        return DepartemenId;
    }

    public void setDepartemenId(Long departemenId) {
        DepartemenId = departemenId;
    }

    public String getGrubAsetKode() {
        return GrubAsetKode;
    }

    public void setGrubAsetKode(String grubAsetKode) {
        GrubAsetKode = grubAsetKode;
    }

    public Long getHargaSekarangBulan() {
        return HargaSekarangBulan;
    }

    public void setHargaSekarangBulan(Long hargaSekarangBulan) {
        HargaSekarangBulan = hargaSekarangBulan;
    }

    public Long getHargaSekarangTahun() {
        return HargaSekarangTahun;
    }

    public void setHargaSekarangTahun(Long hargaSekarangTahun) {
        HargaSekarangTahun = hargaSekarangTahun;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getJobsiteId() {
        return JobsiteId;
    }

    public void setJobsiteId(Long jobsiteId) {
        JobsiteId = jobsiteId;
    }

    public String getKodeDataAset() {
        return KodeDataAset;
    }

    public void setKodeDataAset(String kodeDataAset) {
        KodeDataAset = kodeDataAset;
    }

    public String getLokasi() {
        return Lokasi;
    }

    public void setLokasi(String lokasi) {
        Lokasi = lokasi;
    }

    public Long getMasaPakaiBulan() {
        return MasaPakaiBulan;
    }

    public void setMasaPakaiBulan(Long masaPakaiBulan) {
        MasaPakaiBulan = masaPakaiBulan;
    }

    public Long getMasaPakaiTahun() {
        return MasaPakaiTahun;
    }

    public void setMasaPakaiTahun(Long masaPakaiTahun) {
        MasaPakaiTahun = masaPakaiTahun;
    }

    public Long getMerekId() {
        return MerekId;
    }

    public void setMerekId(Long merekId) {
        MerekId = merekId;
    }

    public Long getNilaiSisa() {
        return NilaiSisa;
    }

    public void setNilaiSisa(Long nilaiSisa) {
        NilaiSisa = nilaiSisa;
    }

    public String getNoRegistrasi() {
        return NoRegistrasi;
    }

    public void setNoRegistrasi(String noRegistrasi) {
        NoRegistrasi = noRegistrasi;
    }

    public Long getPenyusutanPerBulan() {
        return PenyusutanPerBulan;
    }

    public void setPenyusutanPerBulan(Long penyusutanPerBulan) {
        PenyusutanPerBulan = penyusutanPerBulan;
    }

    public Long getPenyusutanPerTahun() {
        return PenyusutanPerTahun;
    }

    public void setPenyusutanPerTahun(Long penyusutanPerTahun) {
        PenyusutanPerTahun = penyusutanPerTahun;
    }

    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        SerialNumber = serialNumber;
    }

    public String getSpesifikasi() {
        return Spesifikasi;
    }

    public void setSpesifikasi(String spesifikasi) {
        Spesifikasi = spesifikasi;
    }

    public String getTanggalRegistrasi() {
        return TanggalRegistrasi;
    }

    public void setTanggalRegistrasi(String tanggalRegistrasi) {
        TanggalRegistrasi = tanggalRegistrasi;
    }

    public Long getTipeId() {
        return TipeId;
    }

    public void setTipeId(Long tipeId) {
        TipeId = tipeId;
    }

    public String getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        UpdatedAt = updatedAt;
    }

    public Long getUrut() {
        return Urut;
    }

    public void setUrut(Long urut) {
        Urut = urut;
    }

    public Long getVendorId() {
        return VendorId;
    }

    public void setVendorId(Long vendorId) {
        VendorId = vendorId;
    }

}
