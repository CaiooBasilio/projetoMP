package br.com.mpmetalurgica.almoxarifado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mpmetalurgica.almoxarifado.model.Categoria;
import br.com.mpmetalurgica.almoxarifado.repository.CategoriaRepository;

@Service
public class CategoriaService {
    
    @Autowired //ele vai estanciar sem vc precisar da aquele new
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listaCategorias(){
        return categoriaRepository.findAll();
    }


    public boolean existsByNomeCategoria(String nome){
        return categoriaRepository.existsByNomeCategoria(nome);
    }

    public List<Categoria> findByLocal(String nome){
        return categoriaRepository.findByLocal(nome);
    }

    public void deleteByNome(String nome){
        categoriaRepository.deleteByNomeCategoria(nome);
    }

    public void saveCategoria(Categoria categoria){
        categoriaRepository.save(categoria);
    }

}
