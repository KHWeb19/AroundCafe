package com.example.demo.security.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
public class JwtDto {

    private final String type = "Bearer";
    private final Long memNo;
    private final String accessToken;
    private final Long accessTokenExp;
    private final String refreshToken;
    private final Long refreshTokenExp;
    private final String email;
    private final String nickname;
    private final String role;

    private final Long cafeNo;

    @Builder
    public JwtDto(Long memNo, String accessToken, Long accessTokenExp, String refreshToken, Long refreshTokenExp, String email, String nickname, String role, Long cafeNo) {
        this.memNo = memNo;
        this.accessToken = accessToken;
        this.accessTokenExp = accessTokenExp;
        this.refreshToken = refreshToken;
        this.refreshTokenExp = refreshTokenExp;
        this.email = email;
        this.nickname = nickname;
        this.role = role;
        this.cafeNo = cafeNo;
    }
}
