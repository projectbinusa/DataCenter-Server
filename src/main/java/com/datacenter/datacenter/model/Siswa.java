package com.datacenter.datacenter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "dataSiswa")
public class Siswa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "namaMurid")
    private String namaMurid;

    @Column(name = "tanggalLahir")
    private String tanggalLahir;

    @Column(name = "tempatLahir")
    private String tempatLahir;

    @Column(name = "umur")
    private String umur;

    @Column(name = "agama")
    private String agama;

    @Column(name = "gender")
    private String gender;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaMurid() {
        return namaMurid;
    }

    public void setNamaMurid(String namaMurid) {
        this.namaMurid = namaMurid;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
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

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getNamaOrtu() {
        return namaOrtu;
    }

    public void setNamaOrtu(String namaOrtu) {
        this.namaOrtu = namaOrtu;
    }

    public String getNoTeleponOrtu() {
        return noTeleponOrtu;
    }

    public void setNoTeleponOrtu(String noTeleponOrtu) {
        this.noTeleponOrtu = noTeleponOrtu;
    }

    @Column(name = "kelas")
    private String kelas;

    @Column(name = "namaOrtu")
    private String namaOrtu;

    @Column(name = "noTeleponOrtu")
    private String noTeleponOrtu;

    @ManyToOne
    private Sekolah sekolah;

    private int tahunDaftar;

    public Siswa() {
    }

    public Siswa(Long id, String namaMurid, String tanggalLahir, String tempatLahir, String umur,
                 String gender, String agama,
                 String kelas, String namaOrtu, String noTeleponOrtu,  Sekolah sekolah) {
        this.id = id;
        this.namaMurid = namaMurid;
        this.tanggalLahir = tanggalLahir;
        this.tempatLahir = tempatLahir;
        this.umur = umur;
        this.gender = gender;
        this.agama = agama;
        this.kelas = kelas;
        this.namaOrtu = namaOrtu;
        this.noTeleponOrtu = noTeleponOrtu;

        this.sekolah = sekolah;
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
