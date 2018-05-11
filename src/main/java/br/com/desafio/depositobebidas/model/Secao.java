package br.com.desafio.depositobebidas.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Secao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nome;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tipo_bebida_id")
    private Bebida tipoBebida;
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
}
