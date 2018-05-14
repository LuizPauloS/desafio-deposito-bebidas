package br.com.desafio.depositobebidas.repository;

import br.com.desafio.depositobebidas.model.Registro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RegistroRepository extends JpaRepository<Registro, Long>{
    
    @Query("SELECT r FROM Registro r WHERE registro = 'Entrada' ORDER BY horario, secao_id DESC")
    List<Registro> findRegistrosEntradaOrByDataAndSecao();
    
    @Query("SELECT r FROM Registro r WHERE registro = 'Saída' ORDER BY horario, secao_id DESC")
    List<Registro> findRegistrosSaidaOrByDataAndSecao();
    
}
