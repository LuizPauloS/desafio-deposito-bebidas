package br.com.desafio.depositobebidas.resource;

import br.com.desafio.depositobebidas.model.Bebida;
import br.com.desafio.depositobebidas.model.Estoque;
import br.com.desafio.depositobebidas.model.Registro;
import br.com.desafio.depositobebidas.model.Secao;
import br.com.desafio.depositobebidas.service.EstoqueService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estoque")
public class EstoqueResource {

    @Autowired
    private EstoqueService estoqueService;

    @GetMapping(value = "/total")
    @ApiOperation(value = "Recurso para listar volume total de bebidas em estoque.", response = Registro.class,
            responseContainer = "List")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista volume total."),
        @ApiResponse(code = 204, message = "Sem conteúdo para retornar!")})
    public ResponseEntity<String> getVolumeTotalBebidasEstoque() {
        String totalVolumeBebidasEstoque = this.estoqueService.getVolumeTotalEstoque();
        if (!"".equals(totalVolumeBebidasEstoque)) {
            return ResponseEntity.ok("Volume total encontrado no estoque: "
                    + totalVolumeBebidasEstoque + " litros!");
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/total/{idBebida}")
    @ApiOperation(value = "Recurso retorna mensagem com volume total em estoque de determinado tipo de bebida.", response = String.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna mensagem informando volume total em estoque por tipo."),
        @ApiResponse(code = 404, message = "Conteúdo não encontrado!")})
    public ResponseEntity<String> getVolumeTotalBebidasEstoquePorTipo(@PathVariable("idBebida") Long idBebida) {
        String totalVolumePorTipoBebida = this.estoqueService.getVolumeTotalBebidasEstoque(idBebida);
        Bebida bebida = this.estoqueService.recuperaBebidaPeloId(idBebida);
        if (!"".equals(totalVolumePorTipoBebida) && bebida != null) {
            return ResponseEntity.ok("Estoque possui volume total de " + (!"".equals(totalVolumePorTipoBebida) ? totalVolumePorTipoBebida : "0")
                    + " litros " + (bebida.getTipo() != null ? "de bebidas " + bebida.getTipo() : "") + "!");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/disponiveis/saida/{idBebida}/{volume}")
    @ApiOperation(value = "Recurso para listar todas as seções com disponibilidade para "
            + "vender determinados tipos de bebidas.", response = Secao.class,
            responseContainer = "List")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de seções que contém a volume "
                + "informado para venda de determinado tipo de bebida."),
        @ApiResponse(code = 204, message = "Sem conteúdo para retornar!")})
    public ResponseEntity<List<Secao>> getSecoesDisponiveisVendaPorTipoBebidaEVolume(
            @PathVariable("idBebida") Long idBebida,
            @PathVariable("volume") Double volume) {
        List<Secao> secoesDisponiveisSaida = this.estoqueService.getSecoesDisponiveisVendaPorTipoBebidaEVolume(idBebida, volume);
        if (!secoesDisponiveisSaida.isEmpty()) {
            return new ResponseEntity<>(secoesDisponiveisSaida, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/disponiveis/entrada/{idBebida}/{volume}")
    @ApiOperation(value = "Recurso para listar todas as seções com tipo de bebida e "
            + "volume informado como parâmetros.", response = Secao.class,
            responseContainer = "List")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de seções que podem armazenar tipo e volume informado."),
        @ApiResponse(code = 204, message = "Sem conteúdo para retornar!")})
    public ResponseEntity<List<Secao>> getSecoesDisponiveisArmazenamentoPorTipoBebidaEVolume(
            @PathVariable("idBebida") Long idBebida,
            @PathVariable("volume") Double volume) {
        List<Secao> secoesDisponiveisEntrada = estoqueService.getSecoesDisponiveisPorTipoBebidaEVolume(idBebida, volume);
        if (!secoesDisponiveisEntrada.isEmpty()) {
            return new ResponseEntity<>(secoesDisponiveisEntrada, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/entrada")
    @ApiOperation(value = "Recurso para cadastrar entrada de bebidas no estoque.", response = String.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna mensagem informando entrada no estoque."),
        @ApiResponse(code = 422, message = "Bebida não adicionada ao estoque! Verifique se está informando corretamente os dados do estoque.")})
    public ResponseEntity<String> addBebidasEstoque(@RequestBody Estoque estoque) {
        if (!"".equals(estoque.getIdBebida()) && estoque.getVolume()!= null
                && !"".equals(estoque.getResponsavel())) {
            String retornoAddEstoque = this.estoqueService.adicionaBebidasEstoque(estoque);
            return ResponseEntity.ok(retornoAddEstoque);
        }
        return ResponseEntity.unprocessableEntity().body("Bebida não adicionada ao estoque! "
                + "Verifique se está informando corretamente os dados do estoque.");
    }

    @PutMapping(value = "/saida")
    @ApiOperation(value = "Recurso para cadastrar saída de bebidas do estoque", response = String.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna mensagem informando saída do estoque."),
        @ApiResponse(code = 422, message = "Bebida não pode ser retirada do estoque!")})
    public ResponseEntity<String> retiraBebidasEstoque(@RequestBody Estoque estoque) {
        if (!"".equals(estoque.getTipoBebida()) && estoque.getVolume()!= null
                && !"".equals(estoque.getResponsavel())) {
            String retornoRetiraBebidaEstoque = this.estoqueService.retiraBebidasEstoque(estoque);
            return ResponseEntity.ok(retornoRetiraBebidaEstoque);
        }
        return ResponseEntity.unprocessableEntity().body("Bebida não pode ser retirada do estoque!");
    }
}
