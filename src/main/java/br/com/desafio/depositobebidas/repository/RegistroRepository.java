package br.com.desafio.depositobebidas.repository;

import br.com.desafio.depositobebidas.model.Registro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroRepository extends JpaRepository<Registro, Long>{
    
}
