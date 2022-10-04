package br.com.itbeta.newlife.repository;

import br.com.itbeta.newlife.model.Apartamento;
import br.com.itbeta.newlife.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>, JpaSpecificationExecutor<Funcionario> {


}
