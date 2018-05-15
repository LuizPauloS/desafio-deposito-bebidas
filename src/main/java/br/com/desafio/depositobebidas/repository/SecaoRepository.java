package br.com.desafio.depositobebidas.repository;

import br.com.desafio.depositobebidas.model.Secao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SecaoRepository extends JpaRepository<Secao, Long> {

    Secao findSecaoById(Long id);
    
    Secao findSecaoByNome(String nome);

    @Query("SELECT SUM(volume) FROM Secao")
    Double findVolumeTotalEstoque();

    @Query("SELECT SUM(volume) FROM Secao WHERE bebida_id = ?1")
    Double findVolumeTotalEstoqueByTipoBebida(@Param("id") Long id);

    @Query("SELECT s FROM Secao s WHERE bebida_id IS NOT NULL OR volume != 0")
    List<Secao> findSecoesDisponiveisVenda();
}
