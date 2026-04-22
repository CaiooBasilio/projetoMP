package br.com.mpmetalurgica.almoxarifado.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.mpmetalurgica.almoxarifado.DTO.ErroDTO;
import br.com.mpmetalurgica.almoxarifado.model.Funcionarios;
import br.com.mpmetalurgica.almoxarifado.model.ItensEstoque;
import br.com.mpmetalurgica.almoxarifado.service.FuncionariosService;


@RequestMapping("/funcionarios")
@CrossOrigin(origins = "*")
@RestController
public class FuncionariosController {
    
    FuncionariosService service;

    public FuncionariosController(FuncionariosService service){
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<Funcionarios>> listaFuncionarios() {
        return ResponseEntity.ok(service.todosFuncionarios());
    }

    @PostMapping()
    public ResponseEntity<?> novoFuncionario(@RequestBody Funcionarios novFuncionarios) {
        if(novFuncionarios.getNomeFuncionario() == null || novFuncionarios.getNomeFuncionario().isBlank()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErroDTO("Funcionario com nome vazio", LocalDateTime.now()));
        }

        if (novFuncionarios.getContato() == null || novFuncionarios.getContato().isBlank()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErroDTO("Funcionario com contato vazio", LocalDateTime.now()));
        }

        service.saveFuncionarios(novFuncionarios);
        return ResponseEntity.ok(novFuncionarios);
    }
    
    @DeleteMapping("/{nome}")
    public ResponseEntity<?> excluiFuncionarioPorNome(@PathVariable String nome){
        if(service.existsByNomeFuncionario(nome)){
            service.deleteByNomeFuncionario(nome);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroDTO("Nome do funcionario não encontrado", LocalDateTime.now()));
    }
}
