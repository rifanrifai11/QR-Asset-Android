
package com.app.britech.riung.models;

import com.google.gson.annotations.SerializedName;

public class Role {

    @SerializedName("created_at")
    private String CreatedAt;
    @SerializedName("description")
    private Object Description;
    @SerializedName("display_name")
    private String DisplayName;
    @SerializedName("id")
    private Long Id;
    @SerializedName("name")
    private String Name;
    @SerializedName("pivot")
    private com.app.britech.riung.models.Pivot Pivot;
    @SerializedName("updated_at")
    private String UpdatedAt;

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        CreatedAt = createdAt;
    }

    public Object getDescription() {
        return Description;
    }

    public void setDescription(Object description) {
        Description = description;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public com.app.britech.riung.models.Pivot getPivot() {
        return Pivot;
    }

    public void setPivot(com.app.britech.riung.models.Pivot pivot) {
        Pivot = pivot;
    }

    public String getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        UpdatedAt = updatedAt;
    }

}
