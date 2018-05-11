package br.com.desafio.depositobebidas.service;

import br.com.desafio.depositobebidas.model.Bebida;
import java.util.List;

public interface BebidaService {

    List<Bebida> buscaTiposDeBebidas();

    Bebida adicionarTipoBebida(Bebida bebida);
}
