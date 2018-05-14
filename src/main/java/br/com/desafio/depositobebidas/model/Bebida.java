package br.com.desafio.depositobebidas.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bebida implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "ID do tipo de bebida")
    private Long id;
    @ApiModelProperty(notes = "Nome do tipo de bebida", required = true)
    @Column(name = "tipo", unique = true, nullable = false)
    private String tipo;
    @ApiModelProperty(notes = "Volume máximo que o tipo pode ter em estoque")
    @Column(name = "volume_maximo")
    private Double volumeMaximo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getVolumeMaximo() {
        return volumeMaximo;
    }

    public void setVolumeMaximo(Double volumeMaximo) {
        this.volumeMaximo = volumeMaximo;
    }

}
