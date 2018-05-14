package br.com.desafio.depositobebidas.resource;

import br.com.desafio.depositobebidas.model.Bebida;
import br.com.desafio.depositobebidas.service.BebidaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bebidas")
public class BebidaResource {

    @Autowired
    private BebidaService bebidaService;

    @GetMapping
    @ApiOperation(value = "Recurso para listar tipos de bebidas.", response = Bebida.class,
            responseContainer = "List")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de tipos de bebidas."),
        @ApiResponse(code = 204, message = "Sem conteúdo para retornar!")
    })
    public ResponseEntity<List<Bebida>> getAllTiposDeBebidas() {
        List<Bebida> bebidas = this.bebidaService.buscaTiposDeBebidas();
        if (bebidas != null && !bebidas.isEmpty()) {
            return new ResponseEntity<>(bebidas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = "/cadastro")
    @ApiOperation(value = "Retorna tipo de bebida cadastrado.", response = Bebida.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna objeto json com tipo de bebida."),
        @ApiResponse(code = 204, message = "Sem conteúdo para retornar!")
    })
    public ResponseEntity<Bebida> addTipoDeBebida(@RequestBody Bebida bebida) {
        if (bebida != null) {
            Bebida b = this.bebidaService.adicionaTipoBebida(bebida);
            return bebida == b ? ResponseEntity.status(201).body(bebida) : ResponseEntity.ok(b);
        }
        return ResponseEntity.noContent().build();
    }
}
