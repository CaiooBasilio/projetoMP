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

    public boolean existsById(Integer id){
        return categoriaRepository.existsById(id);
    }

    public List<Categoria> buscaCategorias(String nome){
        return categoriaRepository.findByNomeCategoriaContainingIgnoreCase(nome);
    }

    public void deleteById(Integer id){
        categoriaRepository.deleteById(id);
    }

    public void saveCategoria(Categoria categoria){
        categoriaRepository.save(categoria);
    }

}
