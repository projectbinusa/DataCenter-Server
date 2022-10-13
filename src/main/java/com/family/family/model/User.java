package com.family.family.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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


    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Siswa> siswa;

    public User() {
    }

    public User(String email, String password, String namaSekolah, String alamatSekolah, String teleponSekolah) {
        this.email = email;
        this.password = password;
        this.namaSekolah = namaSekolah;
        this.alamatSekolah = alamatSekolah;
        this.teleponSekolah = teleponSekolah;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<Siswa> getSiswa() {
        return siswa;
    }

    public void setSiswa(List<Siswa> siswa) {
        this.siswa = siswa;
    }
}
