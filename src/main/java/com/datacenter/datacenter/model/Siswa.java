package com.datacenter.datacenter.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "dataSiswa")
public class Siswa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "namaSiswa")
    private String namaSiswa;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "tanggalLahir")
    private Date tanggalLahir;

    @Column(name = "tempatLahir")
    private String tempatLahir;

    @Column(name = "agama")
    private String agama;

    @Column(name = "gender")
    private String gender;

    @ManyToOne
    private Sekolah sekolah;

    private int tahunDaftar;

    public Siswa() {
    }

    public Siswa(Long id, String namaSiswa, Date tanggalLahir, String tempatLahir, String agama, String gender, Sekolah sekolah) {
        this.id = id;
        this.namaSiswa = namaSiswa;
        this.tanggalLahir = tanggalLahir;
        this.tempatLahir = tempatLahir;
        this.agama = agama;
        this.gender = gender;
        this.sekolah = sekolah;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaSiswa() {
        return namaSiswa;
    }

    public void setNamaSiswa(String namaSiswa) {
        this.namaSiswa = namaSiswa;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Sekolah getSekolah() {
        return sekolah;
    }

    public void setSekolah(Sekolah sekolah) {
        this.sekolah = sekolah;
    }

    public int getTahunDaftar() {
        return tahunDaftar;
    }

    public void setTahunDaftar(int tahunDaftar) {
        this.tahunDaftar = tahunDaftar;
    }
}
