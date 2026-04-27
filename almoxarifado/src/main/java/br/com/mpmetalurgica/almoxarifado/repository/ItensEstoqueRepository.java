package br.com.mpmetalurgica.almoxarifado.repository;

import br.com.mpmetalurgica.almoxarifado.model.ItensEstoque;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ItensEstoqueRepository extends JpaRepository<ItensEstoque, Integer>{
       
    public boolean existsByNomeDoItem (String nome);

    public void deleteByNomeDoItem(String nome);

    public List<ItensEstoque> findByNomeDoItemContainingIgnoreCase(String nome);
}
