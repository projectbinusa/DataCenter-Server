package com.datacenter.datacenter.payload.response;

import com.datacenter.datacenter.model.Sekolah;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String email;

    private Sekolah sekolah;

    public JwtResponse(String accessToken, Long id, String email, Sekolah sekolah) {
        this.token = accessToken;
        this.id = id;
        this.email = email;
        this.sekolah = sekolah;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public Sekolah getSekolah() {
        return sekolah;
    }

    public void setSekolah(Sekolah sekolah) {
        this.sekolah = sekolah;
    }
}
