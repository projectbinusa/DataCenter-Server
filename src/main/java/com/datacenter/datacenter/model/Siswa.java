package com.datacenter.datacenter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "dataSiswa")
public class Siswa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "namaSiswa")
    private String namaSiswa;

    @Column(name = "tanggalLahir")
    private String tanggalLahir;

    @Column(name = "tempatLahir")
    private String tempatLahir;

    @Column(name = "agama")
    private String agama;

    @Column(name = "gender")
    private String gender;
    
    @ManyToOne
    private Sekolah sekolah;

    public Siswa() {
    }

    public Siswa(Long id, String namaSiswa, String tanggalLahir, String tempatLahir, String agama, String gender, Sekolah sekolah) {
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
}
