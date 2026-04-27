package br.com.mpmetalurgica.almoxarifado.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.mpmetalurgica.almoxarifado.DTO.ErroDTO;
import br.com.mpmetalurgica.almoxarifado.model.ItensEstoque;
import br.com.mpmetalurgica.almoxarifado.service.ItensEstoqueService;



@RestController
@RequestMapping("/item")
@CrossOrigin(origins = "*")
public class ItensEstoqueController {

    private ItensEstoqueService service;

    public ItensEstoqueController(ItensEstoqueService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<ItensEstoque>> getItens() {
        return ResponseEntity.ok(service.getItens());
    }

    // CORRIGIDO: era @GetMapping("/{nome}") com @RequestParam — agora usa /buscar?nome=xxx
    @GetMapping("/buscar")
    public ResponseEntity<?> procuraPorNome(@RequestParam String nome) {
        List<ItensEstoque> nomesEncotrados = service.findNome(nome);

        if (nomesEncotrados.size() <= 0)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroDTO("Nome do item não encontrada", LocalDateTime.now()));
        else
            return ResponseEntity.ok(nomesEncotrados);
    }

    // @PatchMapping("/{id}/nome")
    // public ResponseEntity<?> atualizaNomeItem(@PathVariable Integer id, @RequestBody String novoNome) {
    //     for (ItensEstoque est : service.getItens()) {
    //         if (est.getId().equals(id)) {
    //             est.setNomeDoItem(novoNome);
    //             service.salvarItem(est);
    //             return ResponseEntity.ok().body(novoNome);
    //         }
    //     }
    //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroDTO("Nome do item não encontrada", LocalDateTime.now()));
    // }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizaItem(@PathVariable Integer id, @RequestBody ItensEstoque itemAtualizado){
        for (ItensEstoque est : service.getItens()){
            if(est.getId().equals(id)){
                est.setNomeDoItem(itemAtualizado.getNomeDoItem());
                est.setCategoriaItem(itemAtualizado.getCategoriaItem());
                est.setQuantidade(itemAtualizado.getQuantidade());
                est.setDescricaoItem(itemAtualizado.getDescricaoItem());
                est.setConcerto(itemAtualizado.isConcerto());

                service.salvarItem(itemAtualizado);
                return ResponseEntity.ok().body(itemAtualizado);
            }
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroDTO("Item não encontrado", LocalDateTime.now()));
    }

    @PostMapping()
    public ResponseEntity<?> postMethodName(@RequestBody ItensEstoque newItem) {
        
        if (newItem.getNomeDoItem() == null || newItem.getNomeDoItem().isBlank()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new ErroDTO("Item com nome vazio", LocalDateTime.now()));
        }
        if (newItem.getQuantidade() <0) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new ErroDTO("Item não pode ser negativo", LocalDateTime.now()));
        }
        for(ItensEstoque est : service.getItens()){
            if (est.getNomeDoItem().equalsIgnoreCase(newItem.getNomeDoItem())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new ErroDTO("Item ja existente", LocalDateTime.now())); 
            }
        }

        service.salvarItem(newItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(newItem);
    }
    

    // NOVO: atualiza o campo concerto pelo id
    // @PatchMapping("/{id}/concerto")
    // public ResponseEntity<?> atualizaConcerto(@PathVariable Integer id, @RequestBody Boolean concerto) {
    //     for (ItensEstoque est : service.getItens()) {
    //         if (est.getId().equals(id)) {
    //             est.setConcerto(concerto);
    //             service.salvarItem(est);
    //             return ResponseEntity.ok().body(concerto);
    //         }
    //     }
    //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroDTO("Item não encontrado", LocalDateTime.now()));
    // }

    // CORRIGIDO: era /{nome}/quantidade — agora usa /{id}/quantidade para evitar conflito de rota
    @PatchMapping("/{id}/quantidade")
    public ResponseEntity<?> atualizaQuantidade(@PathVariable Integer id, @RequestBody Integer quantidadeRetirada) {
        for (ItensEstoque est : service.getItens()) {
            if (est.getId().equals(id)) {
                if (est.getQuantidade() > quantidadeRetirada) {
                    int quantidade = est.getQuantidade() - quantidadeRetirada;
                    est.setQuantidade(quantidade);
                    service.salvarItem(est);
                    return ResponseEntity.ok().body(quantidade);
                }
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroDTO("Nome do item não encontrada", LocalDateTime.now()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletaItem(@PathVariable Integer id) {
        if (service.exitePorId(id)) {
            service.deletaItem(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroDTO("Nome do item não encontrada", LocalDateTime.now()));
    }
}