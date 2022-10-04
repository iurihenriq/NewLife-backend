package br.com.itbeta.newlife.repository;

import br.com.itbeta.newlife.model.Apartamento;
import br.com.itbeta.newlife.repository.projections.ApartamentoDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartamentoRepository extends JpaRepository<Apartamento, Long>, JpaSpecificationExecutor<Apartamento> {

    @Query(value = "select numero from Apartamento", nativeQuery = true)
    public Page<ApartamentoDetails> findAllApartamento(Pageable pageable);

    @Query(value = "select * from Apartamento", nativeQuery = true)
    List<ApartamentoDetails> findAllAptos();
}
