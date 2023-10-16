package com.example.emotionalsongback.response;

import com.example.emotionalsongback.dto.CanzoneDto;

import java.util.List;

public class CanzoniResponse {
    private List<CanzoneDto> canzoni;
    private long numeroPagine;

    private long numeroCanzoni;

    public CanzoniResponse(List<CanzoneDto> canzoni, long numeroPagine, long numeroCanzoni) {
        this.canzoni = canzoni;
        this.numeroPagine = numeroPagine;
        this.numeroCanzoni = numeroCanzoni;
    }

    public List<CanzoneDto> getCanzoni() {
        return canzoni;
    }

    public long getNumeroPagine() {
        return numeroPagine;
    }

    public long getNumeroCanzoni() {
        return numeroCanzoni;
    }


}
