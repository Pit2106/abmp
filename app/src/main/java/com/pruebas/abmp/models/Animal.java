package com.pruebas.abmp.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Animal {

    @SerializedName("nombre_cient_fico")
    @Expose
    private String name;
    @SerializedName("reino")
    @Expose
    private String kingdom;
    @SerializedName("clase")
    @Expose
    private String kind;
    @SerializedName("orden")
    @Expose
    private String order;
    @SerializedName("familia")
    @Expose
    private String family;
    @SerializedName("g_nero")
    @Expose
    private String gender;
    @SerializedName("estado_de_amenaza")
    @Expose
    private String ext;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKingdom() {
        return kingdom;
    }

    public void setKingdom(String kingdom) {
        this.kingdom = kingdom;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}