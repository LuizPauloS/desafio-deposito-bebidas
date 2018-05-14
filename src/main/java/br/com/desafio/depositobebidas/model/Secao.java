package br.com.desafio.depositobebidas.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Secao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "ID da seção")
    private Long id;
    @Column(nullable = false)
    @ApiModelProperty(notes = "Nome da seção", required = true)
    private String nome;
    @ApiModelProperty(notes = "Tipo de bebida registrada atualmente na seção")
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Bebida bebida;
    @ApiModelProperty(notes = "Volume registrado atualmente na seção")
    @Column(name = "volume", precision = 10, scale = 2)
    private Double volume;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
}
