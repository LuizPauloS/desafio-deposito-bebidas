package br.com.desafio.depositobebidas.service;

import br.com.desafio.depositobebidas.model.Bebida;
import br.com.desafio.depositobebidas.repository.BebidaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class BebidaServiceTest {

    @MockBean
    private BebidaRepository bebidaRepository;
    private BebidaService bebidaService;
    private Bebida bebida;
    private static final String TIPO = "Alcoólicas";

    @Before
    public void setUp() throws Exception {
        bebidaService = new BebidaService(bebidaRepository);
        bebida = new Bebida();
        bebida.setTipo(TIPO);
    }

    @Test
    public void deveAdicionarBebidaNoRepositorio() throws Exception {
        bebidaService.adicionaTipoBebida(bebida);
        verify(bebidaRepository).save(bebida);
    }
    
    @Test
    public void naoDeveAdicionarDoisTiposIguaisDeBebidasNoRepositorio(){
        when(bebidaRepository.findBebidaByTipo(TIPO)).thenReturn(bebida);
    }
}
