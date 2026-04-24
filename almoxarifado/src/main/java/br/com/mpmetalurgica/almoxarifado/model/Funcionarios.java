package br.com.mpmetalurgica.almoxarifado.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "funcionarios")
public class Funcionarios {
    
    @Column(name = "id")
    @Id
    //ele vai basicamente mandar o banco gerar o id 
    // o Interger o mysql entende como isert e o int como update
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nomeFuncionario", nullable = false)
    private String nomeFuncionario;

    @Column(name = "contato", nullable = false)
    private String contato;

    @Column(name = "cargo")
    private String cargo;
}
