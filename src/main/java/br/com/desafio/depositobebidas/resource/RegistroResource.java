package br.com.desafio.depositobebidas.resource;

import br.com.desafio.depositobebidas.model.Registro;
import br.com.desafio.depositobebidas.repository.RegistroRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registros")
public class RegistroResource {

    @Autowired
    private RegistroRepository registroRepository;

    @GetMapping
    public ResponseEntity<List<Registro>> getAllRegistros() {
        List<Registro> registros = this.registroRepository.findAll();
        if (!registros.isEmpty()) {
            return new ResponseEntity<>(registros, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/entrada")
    public ResponseEntity<List<Registro>> getAllRegistrosEntrada() {
        List<Registro> registrosEntrada = this.registroRepository.findRegistrosEntradaOrByDataAndSecao();
        if (!registrosEntrada.isEmpty()) {
            return new ResponseEntity<>(registrosEntrada, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/saida")
    public ResponseEntity<List<Registro>> getAllRegistrosSaida() {
        List<Registro> registrosSaida = this.registroRepository.findRegistrosSaidaOrByDataAndSecao();
        if (!registrosSaida.isEmpty()) {
            return new ResponseEntity<>(registrosSaida, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
