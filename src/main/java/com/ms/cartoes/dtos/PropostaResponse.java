package com.ms.cartoes.dtos;


public class PropostaResponse {

    private String mensagem;

    public PropostaResponse(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
