package br.com.itbeta.newlife.repository;

import br.com.itbeta.newlife.model.Visitante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitanteRepository extends JpaRepository<Visitante, Long>, JpaSpecificationExecutor<Visitante> {
}
