package com.datacenter.datacenter.model;
import org.springframework.stereotype.Component;


import javax.persistence.*;

@Entity
@Table(name="extraSekolah")
public class Extra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "namaExtra")
    private String namaExtra;

    @Column(name = "status")
    private String status;

    @ManyToOne
    Sekolah sekolah;

    public Extra() {

    }

    public Extra(Long id, String namaExtra, String status, Sekolah sekolah) {
        this.id = id;
        this.namaExtra = namaExtra;
        this.status = status;
        this.sekolah = sekolah;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaExtra() {
        return namaExtra;
    }

    public void setNamaExtra(String namaExtra) {
        this.namaExtra = namaExtra;
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
