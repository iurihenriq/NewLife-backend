package br.com.itbeta.newlife.repository;

import br.com.itbeta.newlife.controller.dto.MoradorDto;
import br.com.itbeta.newlife.model.Apartamento;
import br.com.itbeta.newlife.model.Morador;
import br.com.itbeta.newlife.repository.projections.MoradorDetails;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoradorRepository extends JpaRepository<Morador, Long>, JpaSpecificationExecutor<Morador> {

    @Query(value = "SELECT a.numero, m.nome, m.rg, m.cpf, m.telefonePrincipal, m.telefoneSecundario, m.email, m.nomeContatoEmergencial, m.telefoneContatoEmergencial, m.observacoes\n" +
            "FROM morador m JOIN apartamento a ON m.apartamento = a.idApartamento", nativeQuery = true)
    List<MoradorDetails> findAllList();

    @Query(value = "SELECT a.numero, m.nome, m.rg, m.cpf, m.telefonePrincipal, m.telefoneSecundario, m.email, m.nomeContatoEmergencial, m.telefoneContatoEmergencial, m.observacoes\n" +
            "FROM morador m JOIN apartamento a ON m.apartamento = a.idApartamento where (m.nome like %:query%) or (m.cpf like %:query%)", nativeQuery = true)
    List<MoradorDetails> findAllList(@Nullable String query);

}
