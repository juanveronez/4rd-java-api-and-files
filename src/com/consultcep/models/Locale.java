package com.consultcep.models;

public record Locale(
        String erro,
        String cep,
        String logradouro,
        String complemento,
        String unidade,
        String bairro,
        String localidade,
        String uf,
        String estado,
        String regiao
) {
    public boolean getHasError() {
        if (this.erro == null) return false;
        return this.erro.equals("true");
    }
}
