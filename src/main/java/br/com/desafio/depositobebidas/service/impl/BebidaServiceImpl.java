package br.com.desafio.depositobebidas.service.impl;

import br.com.desafio.depositobebidas.model.Bebida;
import br.com.desafio.depositobebidas.repository.BebidaRepository;
import br.com.desafio.depositobebidas.service.BebidaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BebidaServiceImpl implements BebidaService {

    @Autowired
    private final BebidaRepository bebidaRepository;

    public BebidaServiceImpl(BebidaRepository bebidaRepository) {
        this.bebidaRepository = bebidaRepository;
    }

    @Override
    public List<Bebida> buscaTiposDeBebidas() {
        return this.bebidaRepository.findAll();
    }

    @Override
    public Bebida adicionarTipoBebida(Bebida bebida) {
        Bebida b = this.bebidaRepository.findByTipo(bebida.getTipo());
        if (b != null) {
            return b;
        }
        return bebidaRepository.save(bebida);
    }

}
