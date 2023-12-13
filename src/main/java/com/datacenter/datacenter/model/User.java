package com.datacenter.datacenter.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "status")
    private String status;

    private String namaSekolah;

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    @PrimaryKeyJoinColumn
    private Sekolah sekolah;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Sekolah getSekolah() {
        return sekolah;
    }

    public void setSekolah(Sekolah sekolah) {
        this.sekolah = sekolah;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNamaSekolah() {
        return namaSekolah;
    }

    public void setNamaSekolah(String namaSekolah) {
        this.namaSekolah = namaSekolah;
    }
}
