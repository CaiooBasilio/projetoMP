package br.com.mpmetalurgica.almoxarifado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.mpmetalurgica.almoxarifado.model.Funcionarios;
import br.com.mpmetalurgica.almoxarifado.repository.FuncionariosRepository;

public class FuncionariosService {

    @Autowired
    FuncionariosRepository repository;
    
    public List<Funcionarios> todosFuncionarios(){
        return repository.findAll();
    }

    public void saveFuncionarios(Funcionarios f){
        repository.save(f);
    }

    public void deleteByNomeFuncionario(String nome){
        repository.deleteByNomeFuncionario(nome);
    }

    public boolean existsByNomeFuncionario(String nome) {
        return repository.existsByNomeFuncionarioIgnoreCase(nome);
    }


}
