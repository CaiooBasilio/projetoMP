package br.com.mpmetalurgica.almoxarifado.controller;

import java.time.LocalDateTime;
import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mpmetalurgica.almoxarifado.DTO.ErroDTO;
import br.com.mpmetalurgica.almoxarifado.model.Categoria;
import br.com.mpmetalurgica.almoxarifado.service.CategoriaService;
import org.springframework.web.bind.annotation.PutMapping;


    @RestController
    @CrossOrigin(origins="*")
    @RequestMapping("/categoria")
    public class CategoriaController {

        private CategoriaService service;

        public CategoriaController(CategoriaService service){
            this.service = service;
        } 

        // listar as categorias
        @GetMapping
        public ResponseEntity<List<Categoria>> getCategorias() {
            return ResponseEntity.ok(service.listaCategorias());
        }

        // adicionar uma noma categoria
        @PostMapping
        public ResponseEntity<?> novaCategoria(@RequestBody Categoria newCategoria) {

            if (newCategoria.getNomeCategoria() == null || newCategoria.getNomeCategoria().isBlank()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new ErroDTO("Categoria com nome vazio", LocalDateTime.now()));
            }
            if (service.existsByNomeCategoria(newCategoria.getNomeCategoria())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new ErroDTO("Ja existe categoria com o mesmo nome", LocalDateTime.now()));
            }

            service.saveCategoria(newCategoria);
            return ResponseEntity.status(HttpStatus.CREATED).body(newCategoria);
        }

        // tanto o parametro do getmapping quando o da função precisam ser iguais
        // Procura por nome da categoria
        @GetMapping("/buscar")
        public ResponseEntity<?> encontraNome(@RequestParam String nome){
            // for(Categoria achar: service.listaCategorias()){
            //     if (achar.getNomeCategoria().toLowerCase().equals(nome.toLowerCase())) {
            //         return ResponseEntity.ok(achar);
            //     }
            // }
            // return ResponseEntity.notFound().build();

            List<Categoria> valores = service.buscaCategorias(nome);

            if(valores.size()<=0)
                return ResponseEntity.notFound().build();
            else
                return ResponseEntity.ok(valores);
        }

        //deletar categoria pelo nome
        @DeleteMapping("/{id}")
        public ResponseEntity<?> excluiPorNome(@PathVariable Integer id){

            //boolean removido = service.listaCategorias().deleteByNome(nome);
// (name -> name.getNomeCategoria().equals(nome))
            if(service.existsById(id)){
                service.deleteById(id);
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroDTO("Nome de categoria não encontrada", LocalDateTime.now()));
        }

        @PutMapping("/{id}")
        public ResponseEntity<?> atualizarCategoria(@PathVariable Integer id, @RequestBody Categoria categoriaAtualizada) {
            for(Categoria cat: service.listaCategorias()){
                if(cat.getId().equals(id)){
                    cat.setNomeCategoria(categoriaAtualizada.getNomeCategoria());
                    cat.setLinha(categoriaAtualizada.getLinha());
                    cat.setLocal(categoriaAtualizada.getLocal());

                    service.saveCategoria(cat);
                    return ResponseEntity.ok(categoriaAtualizada);
                }
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroDTO("Categoria não existente", LocalDateTime.now()));
        }
    }
