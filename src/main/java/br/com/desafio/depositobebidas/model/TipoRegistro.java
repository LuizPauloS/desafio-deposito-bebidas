package br.com.desafio.depositobebidas.model;

public enum TipoRegistro {
    
    ENTRADA("Entrada"),
    SAIDA("Saída");

    private String tipoRegistro;
    
    private TipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public String getTipoRegistro() {
        return tipoRegistro;
    }
}
