package br.com.desafio.depositobebidas.repository;

import br.com.desafio.depositobebidas.model.Bebida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BebidaRepository extends JpaRepository<Bebida, Long>{
    
    Bebida findBebidaById(Long id);
    
    Bebida findBebidaByTipo(String tipo);
}
