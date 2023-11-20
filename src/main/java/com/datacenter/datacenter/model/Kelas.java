package com.datacenter.datacenter.model;


import javax.persistence.*;

@Entity
@Table(name = "dataKelas")
public class Kelas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "namaKelas")
    private String namaKelas;

    @Column(name = "status")
    private String status;

    @ManyToOne
    private Sekolah sekolah;
    public Kelas(){}


    public Kelas(Long id, String namaKelas, String status,Sekolah sekolah) {
        this.id = id;
        this.namaKelas = namaKelas;
        this.status = status;
        this.sekolah = sekolah;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaKelas() {
        return namaKelas;
    }

    public void setNamaKelas(String namaKelas) {
        this.namaKelas = namaKelas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Sekolah getSekolah() {
        return sekolah;
    }

    public void setSekolah(Sekolah sekolah) {
        this.sekolah = sekolah;
    }
}
