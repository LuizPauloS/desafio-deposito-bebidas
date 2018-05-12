package br.com.desafio.depositobebidas.repository;

import br.com.desafio.depositobebidas.model.Secao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecaoRepository extends JpaRepository<Secao, Long>{
 
    Secao findByNome(String nome);
}
