package br.com.mpmetalurgica.almoxarifado.service;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mpmetalurgica.almoxarifado.model.ItensEstoque;
import br.com.mpmetalurgica.almoxarifado.repository.ItensEstoqueRepository;

@Service
public class ItensEstoqueService {

    @Autowired
    private ItensEstoqueRepository repository;

    public List<ItensEstoque> getItens(){
        return repository.findAll();
    }

    public List<ItensEstoque> findNome(String nome){
        return repository.findByNomeDoItemContainingIgnoreCase(nome);
    }

    public void salvarItem(ItensEstoque novo){
        repository.save(novo);
    }

    public boolean exitePorId(Integer id){
        return repository.existsById(id);
    }

    public void deletaItem(Integer id){
        repository.deleteById(id);
    }
}
