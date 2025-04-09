package com.ms.cartoes.dtos;


public class ClienteResponse {

    private String mensagem;

    public ClienteResponse(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
