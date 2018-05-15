package br.com.desafio.depositobebidas.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Registro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "ID do registro")
    private Long id;
    @ApiModelProperty(notes = "Horário do registro")
    @DateTimeFormat(pattern = "dd/mm/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "horario")
    private Date data;
    @ApiModelProperty(notes = "Tipo do registro(Entrada ou Saída)")
    @Column(name = "registro")
    private String registro;
    @ApiModelProperty(notes = "ID do tipo da bebida relacionada ao registro")
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Bebida bebida;
    @ApiModelProperty(notes = "Volume registro")
    @Column(name = "volume", precision = 10, scale = 2)
    private Double volume;
    @ApiModelProperty(notes = "ID da seção relacionada ao registro")
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Secao secao;
    @ApiModelProperty(notes = "Nome da seção")
    @Column(name = "responsavel")
    private String responsavel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public Bebida getBebida() {
        return bebida;
    }

    public void setBebida(Bebida bebida) {
        this.bebida = bebida;
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
