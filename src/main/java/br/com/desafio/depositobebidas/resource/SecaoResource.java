package br.com.desafio.depositobebidas.resource;

import br.com.desafio.depositobebidas.model.Secao;
import br.com.desafio.depositobebidas.service.SecaoService;
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
@RequestMapping("/secoes")
public class SecaoResource {

    @Autowired
    private SecaoService secaoService;

    @GetMapping
    public ResponseEntity<List<Secao>> getAllSecoes() {
        List<Secao> estoque = this.secaoService.buscaTodasSecoes();
        if (estoque != null && !estoque.isEmpty()) {
            return new ResponseEntity<>(estoque, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/cadastro-secao")
    public ResponseEntity<Secao> addSecaoEstoque(@RequestBody Secao secao) {
        if (secao != null) {
            Secao s = this.secaoService.adicionaSecao(secao);
            return secao == s ? ResponseEntity.status(201).body(secao) : ResponseEntity.ok(s);
        }
        return ResponseEntity.noContent().build();
    }

}
