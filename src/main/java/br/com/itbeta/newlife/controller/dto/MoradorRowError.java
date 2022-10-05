package br.com.itbeta.newlife.controller.dto;

import lombok.Builder;

@Builder
public class MoradorRowError {

    @Builder.Default
    public String errorMsg ="";
}
