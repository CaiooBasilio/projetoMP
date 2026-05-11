package br.com.mpmetalurgica.almoxarifado.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

// ele cria getters e setters, equels, etc 
@Data
@NoArgsConstructor
// O entity fala que essa tabela existe no banco
@Entity
//o name é sempre o mesmo da tabela no banco
@Table(name = "categoria")
public class Categoria {
    
    @Column(name = "id")
    @Id
    //ele vai basicamente mandar o banco gerar o id 
    // o Interger o mysql entende como isert e o int como update
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nomeDaCategoria", nullable = false)
    private String nomeCategoria;

    @Column(name = "local", nullable = false)
    private String local;

    @Column(name = "linha")
    private Integer linha;
}
