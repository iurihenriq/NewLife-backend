package br.com.itbeta.newlife.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "apartamento")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Apartamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idApartamento")
    private Long idApartamento;

    @Column(name = "numero")
    private int numero;

}
