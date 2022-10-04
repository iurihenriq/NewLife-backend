package br.com.itbeta.newlife.model;

import br.com.itbeta.newlife.controller.dto.ApartamentoDto;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "apartamento")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Apartamento {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "idApartamento")
    private Long idApartamento;

    @Column (name = "numero")
    private int numero;

}
