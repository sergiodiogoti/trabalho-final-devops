package com.ms.cartoes.utils;


import java.util.Random;
import java.util.UUID;

public class CartaoUtils {

    /**
     * Gera um número de cartão aleatório.
     * @return O número de cartão gerado.
     */
    public static String gerarNumeroCartao() {
        Random random = new Random();
        StringBuilder numero = new StringBuilder();

        for (int i = 0; i < 16; i++) {
            int digit = random.nextInt(10);  // Gera um número aleatório entre 0 e 9
            numero.append(digit);
        }

        return numero.toString();
    }

    /**
     * Gera um número de conta aleatório.
     * @return O número de conta gerado.
     */
    public static String gerarNumeroConta() {

        Random random = new Random();
        StringBuilder numero = new StringBuilder();

        for (int i = 0; i < 9; i++) {
            int digit = random.nextInt(10);  // Gera um número aleatório entre 0 e 9
            numero.append(digit);
        }

        return numero.toString();
    }

    /**
     * Gera um número de conta aleatório.
     * @return O número de conta gerado.
     */
    public static Integer gerarCCV() {
        Random random = new Random();
        StringBuilder numero = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            int digit = random.nextInt(10);  // Gera um número aleatório entre 0 e 9
            numero.append(digit);
        }

        return Integer.parseInt(numero.toString());
    }
}
