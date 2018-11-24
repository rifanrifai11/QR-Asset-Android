
package com.app.britech.riung.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("alamat")
    private String Alamat;
    @SerializedName("created_at")
    private String CreatedAt;
    @SerializedName("deleted_at")
    private String DeletedAt;
    @SerializedName("email")
    private String Email;
    @SerializedName("foto")
    private String Foto;
    @SerializedName("id")
    private Long Id;
    @SerializedName("kontak")
    private String Kontak;
    @SerializedName("name")
    private String Name;
    @SerializedName("roles")
    private List<Role> Roles;
    @SerializedName("token_device")
    private String TokenDevice;
    @SerializedName("updated_at")
    private String UpdatedAt;
    @SerializedName("verification_token")
    private String VerificationToken;
    @SerializedName("verified")
    private Long Verified;

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String alamat) {
        Alamat = alamat;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getKontak() {
        return Kontak;
    }

    public void setKontak(String kontak) {
        Kontak = kontak;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<Role> getRoles() {
        return Roles;
    }

    public void setRoles(List<Role> roles) {
        Roles = roles;
    }

    public String getTokenDevice() {
        return TokenDevice;
    }

    public void setTokenDevice(String tokenDevice) {
        TokenDevice = tokenDevice;
    }

    public String getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        UpdatedAt = updatedAt;
    }

    public String getVerificationToken() {
        return VerificationToken;
    }

    public void setVerificationToken(String verificationToken) {
        VerificationToken = verificationToken;
    }

    public Long getVerified() {
        return Verified;
    }

    public void setVerified(Long verified) {
        Verified = verified;
    }

}
