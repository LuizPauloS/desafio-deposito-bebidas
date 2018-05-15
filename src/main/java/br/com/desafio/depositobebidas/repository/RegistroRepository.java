package br.com.desafio.depositobebidas.repository;

import br.com.desafio.depositobebidas.model.Registro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RegistroRepository extends JpaRepository<Registro, Long> {

    @Query("SELECT r FROM Registro r WHERE registro = 'Entrada' ORDER BY data ASC")
    List<Registro> findRegistrosEntradaOrdenadoDataAsc();

    @Query("SELECT r FROM Registro r WHERE registro = 'Entrada' ORDER BY secao ASC")
    List<Registro> findRegistrosEntradaOrdenadoSecaoAsc();

    @Query("SELECT r FROM Registro r WHERE registro = 'Entrada' ORDER BY data DESC")
    List<Registro> findRegistrosEntradaOrdenadoDataDesc();

    @Query("SELECT r FROM Registro r WHERE registro = 'Entrada' ORDER BY secao DESC")
    List<Registro> findRegistrosEntradaOrdenadoSecaoDesc();

    
    @Query("SELECT r FROM Registro r WHERE registro = 'Saída' ORDER BY data ASC")
    List<Registro> findRegistrosSaidaOrdenadoDataAsc();

    @Query("SELECT r FROM Registro r WHERE registro = 'Saída' ORDER BY secao ASC")
    List<Registro> findRegistrosSaidaOrdenadoSecaoAsc();

    @Query("SELECT r FROM Registro r WHERE registro = 'Saída' ORDER BY data DESC")
    List<Registro> findRegistrosSaidaOrdenadoDataDesc();

    @Query("SELECT r FROM Registro r WHERE registro = 'Saída' ORDER BY secao DESC")
    List<Registro> findRegistrosSaidaOrdenadoSecaoDesc();
}
