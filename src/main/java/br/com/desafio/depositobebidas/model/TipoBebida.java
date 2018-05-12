package br.com.desafio.depositobebidas.model;

public enum TipoBebida {

    ALCOOLICAS("ALCOÓLICAS"),
    NAO_ALCOOLICAS("NÃO ALCOÓLICAS");

    private String tipoBebida;
    
    private TipoBebida(String tipoBebida) {
        this.tipoBebida = tipoBebida;
    }

    public String getTipoBebida() {
        return tipoBebida;
    }
}
