package com.datacenter.datacenter.model;


import javax.persistence.*;

@Entity
@Table(name="extraSekolah")
public class ExtraSekolah {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "namaExtra")
    private String namaExtra;

    @Column(name = "status")
    private String status;

    public ExtraSekolah(){
    }

    public ExtraSekolah(Long id, String namaExtra, String status) {
        this.id = id;
        this.namaExtra = namaExtra;
        this.status = status;
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
}
