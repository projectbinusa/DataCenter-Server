package com.datacenter.datacenter.model;


import javax.persistence.*;

@Entity
@Table(name = "gelarPendidikan")

public class GelarPendidikan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "namaGelar")
    private String namaGelar;

    @Column(name ="status")
    private String status;
@ManyToOne
Sekolah sekolah;

    public GelarPendidikan(){

    }


    public GelarPendidikan(Long id, String namaGelar, String status,Sekolah sekolah) {
        this.id = id;
        this.namaGelar = namaGelar;
        this.status = status;
        this.sekolah = sekolah;


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaGelar() {
        return namaGelar;
    }

    public void setNamaGelar(String namaGelar) {
        this.namaGelar = namaGelar;
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
