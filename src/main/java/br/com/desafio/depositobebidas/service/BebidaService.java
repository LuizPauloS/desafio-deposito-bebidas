package br.com.desafio.depositobebidas.service;

import br.com.desafio.depositobebidas.model.Bebida;
import br.com.desafio.depositobebidas.model.TipoBebida;
import br.com.desafio.depositobebidas.repository.BebidaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BebidaService {

    @Autowired
    private final BebidaRepository bebidaRepository;

    public BebidaService(BebidaRepository bebidaRepository) {
        this.bebidaRepository = bebidaRepository;
    }

    public List<Bebida> buscaTiposDeBebidas() {
        return this.bebidaRepository.findAll();
    }

    public Bebida adicionaTipoBebida(Bebida bebida) {
        Bebida b = this.bebidaRepository.findByTipo(bebida.getTipo());
        String tipoUpper = "";
        if (b == null) {
            tipoUpper = bebida.getTipo().toUpperCase();
            if (TipoBebida.ALCOOLICAS.getTipoBebida().equals(tipoUpper)) {
                bebida.setVolumeMaximo(500D);
            } else if (TipoBebida.NAO_ALCOOLICAS.getTipoBebida().equals(tipoUpper)) {
                bebida.setVolumeMaximo(400D);
            }
            return bebidaRepository.save(bebida);
        }
        return b;
    }
}
