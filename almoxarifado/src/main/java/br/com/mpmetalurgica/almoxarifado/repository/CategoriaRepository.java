package br.com.mpmetalurgica.almoxarifado.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mpmetalurgica.almoxarifado.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
    //ele olha o nome que esta la no Categoria baseada no nome da classe
    boolean existsByNomeCategoria(String nome);
    public List<Categoria> findByLocal(String nome);

    public void deleteByNomeCategoria(String nome);

} 
