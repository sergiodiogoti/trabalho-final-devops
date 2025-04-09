package com.ms.cartoes.enums;

public enum StatusProposta {
    APROVADA(2, "APROVADA"),
    RECUSADA(3, "RECUSADA");

    private final int codigo;
    private final String descricao;

    StatusProposta(int codigo, String descricao) {
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
