package com.datacenter.datacenter.model;

import javax.persistence.*;


@Entity
@Table(name = "dataGuru")

public class Guru {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "namaGuru")
    private String namaGuru;

    @Column(name = "tempatLahir")
    private String tempatLahir;

    @Column(name = "tanggalLahir")
    private String tanggalLahir;

    @Column(name = "agama")
    private String agama;

    @Column(name = "umur")
    private String umur;
    @Column(name = "gender")
    private String gender;

    @Column(name = "noTelepon")
    private String noTelepon;

    @Column(name = "gelarPendidikan")
    private String gelarPendidikan;

    @Column(name = "statusKawin")
    private String statusKawin;

    @ManyToOne
    private Sekolah sekolah;

    public Guru() {
    }

    public Guru(Long id, String namaGuru, String tanggalLahir, String tempatLahir, String agama, String gender, String noTelepon, String gelarPendidikan, String statusKawin,String umur,Sekolah sekolah) {
        this.id = id;
        this.namaGuru = namaGuru;
        this.tanggalLahir = tanggalLahir;
        this.tempatLahir = tempatLahir;
        this.agama = agama;
        this.gender = gender;
        this.noTelepon = noTelepon;
        this.gelarPendidikan = gelarPendidikan;
        this.statusKawin = statusKawin;
        this.sekolah = sekolah;
        this.umur = umur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaGuru() {
        return namaGuru;
    }

    public void setNamaGuru(String namaGuru) {
        this.namaGuru = namaGuru;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }
    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getGelarPendidikan() {
        return gelarPendidikan;
    }

    public void setGelarPendidikan(String gelarPendidikan) {
        this.gelarPendidikan = gelarPendidikan;
    }

    public String getStatusKawin() {
        return statusKawin;
    }

    public void setStatusKawin(String statusKawin) {
        this.statusKawin = statusKawin;
    }

    public Sekolah getSekolah() {
        return sekolah;
    }

    public void setSekolah(Sekolah sekolah) {
        this.sekolah = sekolah;
    }
}

