package br.com.mpmetalurgica.almoxarifado.model;


// import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "itensDoEstoque")
public class ItensEstoque {
    
    @Column(name = "id")
    @Id
    //ele vai basicamente mandar o banco gerar o id 
    // o Interger o mysql entende como isert e o int como update
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nomeDoItem", nullable = false)
    private String nomeItem;

    @Column(name = "descricaoItem", nullable = false)
    private String descricaoItem;

    @ManyToOne
    @JoinColumn(name = "categoriaDoItem", nullable = false)
    private Categoria categoriaItem;

    @Column(name = "quantidade", nullable = false)
    private int quantidade;
    
    @Column(name = "noConcerto")
    private boolean concerto;
}
