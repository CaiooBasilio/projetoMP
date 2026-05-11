package br.com.mpmetalurgica.almoxarifado.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErroDTO {
    
    private String mensagem;

    private LocalDateTime data;
}

