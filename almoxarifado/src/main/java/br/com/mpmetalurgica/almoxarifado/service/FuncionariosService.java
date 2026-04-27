package br.com.mpmetalurgica.almoxarifado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mpmetalurgica.almoxarifado.model.Funcionarios;
import br.com.mpmetalurgica.almoxarifado.repository.FuncionariosRepository;

@Service
public class FuncionariosService {

    @Autowired
    FuncionariosRepository repository;
    
    public List<Funcionarios> todosFuncionarios(){
        return repository.findAll();
    }

    public void saveFuncionarios(Funcionarios f){
        repository.save(f);
    }

    public void deleteById(Integer id){
        repository.deleteById(id);
    }

    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }


}
