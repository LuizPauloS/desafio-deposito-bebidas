package br.com.desafio.depositobebidas.service;

import br.com.desafio.depositobebidas.model.Secao;
import br.com.desafio.depositobebidas.repository.SecaoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecaoService {

    @Autowired
    private final SecaoRepository secaoRepository;

    public SecaoService(SecaoRepository secaoRepository) {
        this.secaoRepository = secaoRepository;
    }

    public List<Secao> buscaTodasSecoes() {
        return this.secaoRepository.findAll();
    }

    public Secao adicionaSecao(Secao secao) {
        Secao s = this.secaoRepository.findByNome(secao.getNome());
        if (s == null) {
            if (secao.getVolume() == null) {
                secao.setVolume(0D);
            }
            return secaoRepository.save(secao);
        }
        return s;
    }
}