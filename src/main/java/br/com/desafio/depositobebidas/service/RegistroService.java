package br.com.desafio.depositobebidas.service;

import br.com.desafio.depositobebidas.model.Registro;
import br.com.desafio.depositobebidas.repository.RegistroRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroService {

    @Autowired
    private RegistroRepository registroRepository;

    public List<Registro> findAll() {
        return this.registroRepository.findAll();
    }

    public List<Registro> findRegistrosEntradaOrdenado(String coluna, String ordenacao) {
        List<Registro> registrosOrdenados = new ArrayList<>();
        if ("ASC".equals(ordenacao.toUpperCase())) {
            if ("DATA".equals(coluna.toUpperCase())) {
                registrosOrdenados = this.registroRepository.findRegistrosEntradaOrdenadoDataAsc();
            } else if ("SECAO".equals(coluna.toUpperCase())) {
                registrosOrdenados = this.registroRepository.findRegistrosEntradaOrdenadoSecaoAsc();
            }
        } else if ("DESC".equals(ordenacao.toUpperCase())) {
            if ("DATA".equals(coluna.toUpperCase())) {
                registrosOrdenados = this.registroRepository.findRegistrosEntradaOrdenadoDataDesc();
            } else if ("SECAO".equals(coluna.toUpperCase())) {
                registrosOrdenados = this.registroRepository.findRegistrosEntradaOrdenadoSecaoDesc();
            }
        }
        return registrosOrdenados;
    }

    public List<Registro> findRegistrosSaidaOrdenado(String coluna, String ordenacao) {
        List<Registro> registrosOrdenados = new ArrayList<>();
        if ("ASC".equals(ordenacao.toUpperCase())) {
            if ("DATA".equals(coluna.toUpperCase())) {
                registrosOrdenados = this.registroRepository.findRegistrosSaidaOrdenadoDataAsc();
            } else if ("SECAO".equals(coluna.toUpperCase())) {
                registrosOrdenados = this.registroRepository.findRegistrosSaidaOrdenadoSecaoAsc();
            }
        } else if ("DESC".equals(ordenacao.toUpperCase())) {
            if ("DATA".equals(coluna.toUpperCase())) {
                registrosOrdenados = this.registroRepository.findRegistrosSaidaOrdenadoDataDesc();
            } else if ("SECAO".equals(coluna.toUpperCase())) {
                registrosOrdenados = this.registroRepository.findRegistrosSaidaOrdenadoSecaoDesc();
            }
        }
        return registrosOrdenados;
    }

}
