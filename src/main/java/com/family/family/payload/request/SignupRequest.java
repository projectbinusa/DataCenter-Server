package com.family.family.payload.request;

import javax.persistence.Column;
import java.util.Set;

public class SignupRequest {
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "namaSekolah")
    private String namaSekolah;

    @Column(name = "alamatSekolah")
    private String alamatSekolah;

    @Column(name = "teleponSekolah")
    private String teleponSekolah;



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNamaSekolah() {
        return namaSekolah;
    }

    public void setNamaSekolah(String namaSekolah) {
        this.namaSekolah = namaSekolah;
    }

    public String getAlamatSekolah() {
        return alamatSekolah;
    }

    public void setAlamatSekolah(String alamatSekolah) {
        this.alamatSekolah = alamatSekolah;
    }

    public String getTeleponSekolah() {
        return teleponSekolah;
    }

    public void setTeleponSekolah(String teleponSekolah) {
        this.teleponSekolah = teleponSekolah;
    }
}
