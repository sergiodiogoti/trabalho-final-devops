package com.ms.cartoes.enums;

public enum StatusCliente {
    ATIVO(1, "ATIVO"),
    INATIVO(2, "INATIVO");


    private final int codigo;
    private final String descricao;

    StatusCliente(int codigo, String descricao) {
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
