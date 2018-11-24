
package com.app.britech.riung.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataAset {

    @SerializedName("aset_basts")
    private List<AsetBast> AsetBasts;
    @SerializedName("created_at")
    private String CreatedAt;
    @SerializedName("deleted_at")
    private String DeletedAt;
    @SerializedName("departemen")
    private com.app.britech.riung.models.Departemen Departemen;
    @SerializedName("departemen_id")
    private Long DepartemenId;
    @SerializedName("foto1")
    private String Foto1;
    @SerializedName("foto2")
    private String Foto2;
    @SerializedName("foto3")
    private String Foto3;
    @SerializedName("foto4")
    private String Foto4;
    @SerializedName("grub_aset_kode")
    private String GrubAsetKode;
    @SerializedName("grub_asets")
    private com.app.britech.riung.models.GrubAsets GrubAsets;
    @SerializedName("harga_sekarang_bulan")
    private Long HargaSekarangBulan;
    @SerializedName("harga_sekarang_tahun")
    private Long HargaSekarangTahun;
    @SerializedName("id")
    private Long Id;
    @SerializedName("jobsite")
    private com.app.britech.riung.models.Jobsite Jobsite;
    @SerializedName("jobsite_id")
    private Long JobsiteId;
    @SerializedName("kode_data_aset")
    private String KodeDataAset;
    @SerializedName("latest_aset_pembelian")
    private List<AsetPembelian> AsetPembelian;
    @SerializedName("latest_aset_takings")
    private List<AsetTaking> LatestAsetTakings;
    @SerializedName("lokasi")
    private String Lokasi;
    @SerializedName("masa_pakai_bulan")
    private Long MasaPakaiBulan;
    @SerializedName("masa_pakai_tahun")
    private Long MasaPakaiTahun;
    @SerializedName("merek_id")
    private Long MerekId;
    @SerializedName("nilai_sisa")
    private Long NilaiSisa;
    @SerializedName("no_registrasi")
    private String NoRegistrasi;
    @SerializedName("penyusutan_per_bulan")
    private Long PenyusutanPerBulan;
    @SerializedName("penyusutan_per_tahun")
    private Long PenyusutanPerTahun;
    @SerializedName("serial_number")
    private String SerialNumber;
    @SerializedName("spesifikasi")
    private String Spesifikasi;
    @SerializedName("tanggal_registrasi")
    private String TanggalRegistrasi;
    @SerializedName("tipe")
    private com.app.britech.riung.models.Tipe Tipe;
    @SerializedName("tipe_id")
    private Long TipeId;
    @SerializedName("updated_at")
    private String UpdatedAt;
    @SerializedName("urut")
    private Long Urut;
    @SerializedName("vendor")
    private com.app.britech.riung.models.Vendor Vendor;
    @SerializedName("vendor_id")
    private Long VendorId;

    public List<AsetBast> getAsetBasts() {
        return AsetBasts;
    }

    public void setAsetBasts(List<AsetBast> asetBasts) {
        AsetBasts = asetBasts;
    }

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

    public com.app.britech.riung.models.Departemen getDepartemen() {
        return Departemen;
    }

    public void setDepartemen(com.app.britech.riung.models.Departemen departemen) {
        Departemen = departemen;
    }

    public Long getDepartemenId() {
        return DepartemenId;
    }

    public void setDepartemenId(Long departemenId) {
        DepartemenId = departemenId;
    }

    public String getFoto1() {
        return Foto1;
    }

    public void setFoto1(String foto1) {
        Foto1 = foto1;
    }

    public String getFoto2() {
        return Foto2;
    }

    public void setFoto2(String foto2) {
        Foto2 = foto2;
    }

    public String getFoto3() {
        return Foto3;
    }

    public void setFoto3(String foto3) {
        Foto3 = foto3;
    }

    public String getFoto4() {
        return Foto4;
    }

    public void setFoto4(String foto4) {
        Foto4 = foto4;
    }

    public String getGrubAsetKode() {
        return GrubAsetKode;
    }

    public void setGrubAsetKode(String grubAsetKode) {
        GrubAsetKode = grubAsetKode;
    }

    public com.app.britech.riung.models.GrubAsets getGrubAsets() {
        return GrubAsets;
    }

    public void setGrubAsets(com.app.britech.riung.models.GrubAsets grubAsets) {
        GrubAsets = grubAsets;
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

    public com.app.britech.riung.models.Jobsite getJobsite() {
        return Jobsite;
    }

    public void setJobsite(com.app.britech.riung.models.Jobsite jobsite) {
        Jobsite = jobsite;
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

    public List<AsetPembelian> getAsetPembelian() {
        return AsetPembelian;
    }

    public void setAsetPembelian(List<AsetPembelian> asetPembelian) {
        AsetPembelian = asetPembelian;
    }

    public List<AsetTaking> getLatestAsetTakings() {
        return LatestAsetTakings;
    }

    public void setLatestAsetTakings(List<AsetTaking> latestAsetTakings) {
        LatestAsetTakings = latestAsetTakings;
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

    public com.app.britech.riung.models.Tipe getTipe() {
        return Tipe;
    }

    public void setTipe(com.app.britech.riung.models.Tipe tipe) {
        Tipe = tipe;
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

    public com.app.britech.riung.models.Vendor getVendor() {
        return Vendor;
    }

    public void setVendor(com.app.britech.riung.models.Vendor vendor) {
        Vendor = vendor;
    }

    public Long getVendorId() {
        return VendorId;
    }

    public void setVendorId(Long vendorId) {
        VendorId = vendorId;
    }

}
