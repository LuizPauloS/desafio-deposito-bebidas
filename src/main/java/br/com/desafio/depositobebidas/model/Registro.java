package br.com.desafio.depositobebidas.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Registro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "horario")
    private Date horarioRegistro;
    @Column(name = "tipo_registro")
    private String tipoRegistro;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tipo_bebida_id")
    private Bebida tipoBebida;
    @Column(name = "volume", precision = 10, scale = 2)
    private Double volume;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "secao_id")
    private Secao secao;
    @Column(name = "responsavel")
    private String responsavel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getHorarioRegistro() {
        return horarioRegistro;
    }

    public void setHorarioRegistro(Date horarioRegistro) {
        this.horarioRegistro = horarioRegistro;
    }

    public String getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public Bebida getTipoBebida() {
        return tipoBebida;
    }

    public void setTipoBebida(Bebida tipoBebida) {
        this.tipoBebida = tipoBebida;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Secao getSecao() {
        return secao;
    }

    public void setSecao(Secao secao) {
        this.secao = secao;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

}
