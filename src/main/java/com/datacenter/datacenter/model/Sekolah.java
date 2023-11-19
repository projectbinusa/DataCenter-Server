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

    @Column(name = "emailSekolah")
    private String emailSekolah;

    @Column(name = "akreditasiSekolah")
    private String akreditasiSekolah;

    @Column(name = "ruangKelas")
    private Integer ruangKelas;

    @Column(name = "status")
    private String status;

    @Column(name = "informasiSekolah")
    private String informasiSekolah;
    @JsonIgnore
    @OneToOne
    @MapsId
    @JoinColumn(name = "user")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "sekolah", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Siswa> siswa;


    public Sekolah(Long id, String namaSekolah, String alamatSekolah, String teleponSekolah, String emailSekolah, String akreditasiSekolah, Integer ruangKelas, String status,String informasiSekolah, User user, List<Siswa> siswa) {
        this.id = id;
        this.namaSekolah = namaSekolah;
        this.alamatSekolah = alamatSekolah;
        this.teleponSekolah = teleponSekolah;
        this.emailSekolah = emailSekolah;
        this.akreditasiSekolah = akreditasiSekolah;
        this.ruangKelas = ruangKelas;
        this.status = status;
        this.informasiSekolah = informasiSekolah;
        this.user = user;
        this.siswa = siswa;

    }
    public Sekolah() {
        // Initialization code if needed
    }
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

    public String getEmailSekolah() {
        return emailSekolah;
    }

    public void setEmailSekolah(String emailSekolah) {
        this.emailSekolah = emailSekolah;
    }

    public String getAkreditasiSekolah() {
        return akreditasiSekolah;
    }

    public void setAkreditasiSekolah(String akreditasiSekolah) {
        this.akreditasiSekolah = akreditasiSekolah;
    }

    public Integer getRuangKelas() {
        return ruangKelas;
    }

    public void setRuangKelas(Integer ruangKelas) {
        this.ruangKelas = ruangKelas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInformasiSekolah() {
        return informasiSekolah;
    }

    public void setInformasiSekolah(String informasiSekolah) {
        this.informasiSekolah = informasiSekolah;
    }
}
