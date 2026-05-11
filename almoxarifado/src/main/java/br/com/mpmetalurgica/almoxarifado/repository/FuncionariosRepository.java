package br.com.mpmetalurgica.almoxarifado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mpmetalurgica.almoxarifado.model.Funcionarios;

public interface FuncionariosRepository extends JpaRepository<Funcionarios, Integer>{
    
    public boolean existsByNomeFuncionarioIgnoreCase(String nome);

    public void deleteByNomeFuncionario(String nome);

}
