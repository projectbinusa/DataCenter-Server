package com.datacenter.datacenter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sekolah")
public class Sekolah {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "namaSekolah")
    private String namaSekolah;

    @Column(name = "alamatSekolah")
    private String alamatSekolah;

    @Column(name = "teleponSekolah")
    private String teleponSekolah;

    @JsonIgnore
    @OneToOne
    @MapsId
    @JoinColumn(name = "user")
    private User user;

    @OneToMany(mappedBy = "sekolah", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Siswa> siswa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Siswa> getSiswa() {
        return siswa;
    }

    public void setSiswa(List<Siswa> siswa) {
        this.siswa = siswa;
    }
}
