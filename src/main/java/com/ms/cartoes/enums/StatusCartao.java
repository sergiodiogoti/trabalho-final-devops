package com.ms.cartoes.enums;

public enum StatusCartao {
    ATIVO(2, "ATIVO"),
    INATIVO(3, "INATIVO");

    private final int codigo;
    private final String descricao;

    StatusCartao(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
