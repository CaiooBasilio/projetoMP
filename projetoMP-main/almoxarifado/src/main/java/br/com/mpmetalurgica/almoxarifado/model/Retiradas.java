package br.com.mpmetalurgica.almoxarifado.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "retiradas")
public class Retiradas {
    @Column(name = "id")
    @Id
    //ele vai basicamente mandar o banco gerar o id 
    // o Interger o mysql entende como isert e o int como update
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idFuncionario", nullable = false)
    private Funcionarios idFuncionario;

    @ManyToOne
    @JoinColumn(name = "idItem", nullable = false)
    private ItensEstoque idItem;

    @Column(name = "quantidadePega", nullable = false)
    private int quantidadePega;

    @Column(name = "dataPega", nullable = false)
    private LocalDate dataPega;

    @Column(name = "dataDevolucao")
    private String dataDevolucao;
}
