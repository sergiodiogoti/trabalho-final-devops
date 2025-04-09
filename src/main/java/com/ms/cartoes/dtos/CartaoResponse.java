package com.ms.cartoes.dtos;


public class CartaoResponse {

    private String mensagem;

    public CartaoResponse(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
