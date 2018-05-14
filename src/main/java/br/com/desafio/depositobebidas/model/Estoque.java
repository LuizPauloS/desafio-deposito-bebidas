package br.com.desafio.depositobebidas.model;

public class Estoque {

    private Long IdBebida;
    private Long IdSecao;
    private String tipoBebida;
    private Double volume;
    private String responsavel;
    private String nomeSecao;

    public Long getIdBebida() {
        return IdBebida;
    }

    public void setIdBebida(Long IdBebida) {
        this.IdBebida = IdBebida;
    }

    public Long getIdSecao() {
        return IdSecao;
    }

    public void setIdSecao(Long IdSecao) {
        this.IdSecao = IdSecao;
    }

    public String getTipoBebida() {
        return tipoBebida;
    }

    public void setTipoBebida(String tipoBebida) {
        this.tipoBebida = tipoBebida;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getNomeSecao() {
        return nomeSecao;
    }

    public void setNomeSecao(String nomeSecao) {
        this.nomeSecao = nomeSecao;
    }

}
