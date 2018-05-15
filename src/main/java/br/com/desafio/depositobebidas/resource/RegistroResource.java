package br.com.desafio.depositobebidas.resource;

import br.com.desafio.depositobebidas.model.Bebida;
import br.com.desafio.depositobebidas.model.Registro;
import br.com.desafio.depositobebidas.service.RegistroService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registros")
public class RegistroResource {

    @Autowired
    private RegistroService registroService;

    @GetMapping
    @ApiOperation(value = "Recurso para listar todos os registros.", response = Registro.class,
            responseContainer = "List")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de registros."),
        @ApiResponse(code = 204, message = "Sem conteúdo para retornar!")})
    public ResponseEntity<List<Registro>> getAllRegistros() {
        List<Registro> registros = this.registroService.findAll();
        if (!registros.isEmpty()) {
            return new ResponseEntity<>(registros, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/entrada/{coluna}/{ordenacao}")
    @ApiOperation(value = "Recurso para listar registros de entrada "
            + "ordenando por data ou seção e ascendente ou descendente.", response = Bebida.class,
            responseContainer = "List")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de registros entrada."),
        @ApiResponse(code = 204, message = "Sem conteúdo para retornar!")})
    public ResponseEntity<List<Registro>> getAllRegistrosEntrada(
            @PathVariable("coluna") String coluna,
            @PathVariable("ordenacao") String ordenacao) {
        List<Registro> registrosEntrada = this.registroService.findRegistrosEntradaOrdenado(coluna, ordenacao);
        if (!registrosEntrada.isEmpty()) {
            return new ResponseEntity<>(registrosEntrada, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/saida/{coluna}/{ordenacao}")
    @ApiOperation(value = "Recurso para listar registros de saída "
            + "ordenando por data ou seção e ascendente ou descendente.", response = Bebida.class,
            responseContainer = "List")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de registros saída."),
        @ApiResponse(code = 204, message = "Sem conteúdo para retornar!")})
    public ResponseEntity<List<Registro>> getAllRegistrosSaida(
            @PathVariable("coluna") String coluna,
            @PathVariable("ordenacao") String ordenacao) {
        List<Registro> registrosSaida = this.registroService.findRegistrosSaidaOrdenado(coluna, ordenacao);
        if (!registrosSaida.isEmpty()) {
            return new ResponseEntity<>(registrosSaida, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
