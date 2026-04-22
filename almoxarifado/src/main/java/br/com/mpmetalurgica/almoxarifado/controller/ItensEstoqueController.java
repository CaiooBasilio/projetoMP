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

    @GetMapping()
    public ResponseEntity<?> procuraPorNome(@RequestParam String nome) {
        List<ItensEstoque> nomesEncotrados = service.findNome(nome);

        if (nomesEncotrados.size() <=0)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroDTO("Nome do item não encontrada", LocalDateTime.now()));
        else
            return ResponseEntity.ok(nomesEncotrados);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizaNomeItem(@PathVariable Integer id, @RequestBody String novoNome) {
        for(ItensEstoque est: service.getItens()){
            if (est.getId().equals(id)) {
                est.setNomeItem(novoNome);

                service.salvarItem(est);
                return ResponseEntity.ok().body(novoNome);
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroDTO("Nome do item não encontrada", LocalDateTime.now()));
    }

    @PatchMapping("/{nome}")
    public ResponseEntity<?> atualizaQuantidade(@PathVariable String nome, @RequestBody Integer quantidadeRetirada) {
        for(ItensEstoque est: service.getItens()){
            if (est.getNomeItem().equals(nome)) {
                int quantidade;
                if (est.getQuantidade()>quantidadeRetirada) {
                    quantidade = est.getQuantidade()-quantidadeRetirada;
                    est.setQuantidade(quantidade);
                    
                    service.salvarItem(est);
                    return ResponseEntity.ok().body(quantidade);
                }
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroDTO("Nome do item não encontrada", LocalDateTime.now()));    
    }
    
    @DeleteMapping("/nome")
    public ResponseEntity<?> deletaItem(@PathVariable String nome){
        if(service.exitePorNome(nome)){
            service.deletaItem(nome);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroDTO("Nome do item não encontrada", LocalDateTime.now()));
    }
}
