package br.com.itbeta.newlife.service;

import br.com.itbeta.newlife.controller.dto.ApartamentoDto;
import br.com.itbeta.newlife.controller.dto.FuncionarioDto;
import br.com.itbeta.newlife.controller.form.FuncionarioForm;
import br.com.itbeta.newlife.model.Apartamento;
import br.com.itbeta.newlife.model.Funcionario;
import br.com.itbeta.newlife.repository.ApartamentoRepository;
import br.com.itbeta.newlife.repository.FuncionarioRepository;
import br.com.itbeta.newlife.repository.projections.ApartamentoDetails;
import br.com.itbeta.newlife.repository.specifications.ApartamentoSpecification;
import br.com.itbeta.newlife.repository.specifications.FuncionarioSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class ApartamentosService {

    private final ApartamentoRepository repository;

    private final ApartamentoRepository apartamentoRepository;

    public ApartamentoDto findById(Long idApartamento) {
        Apartamento a = this.repository.findById(idApartamento).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new ApartamentoDto(a);
    }

    public Page<Apartamento> findAllPaginated(Pageable pageable){
        return this.repository.findAll(pageable);
    }

    public List<ApartamentoDetails> findAll(){
        return this.repository.findAllAptos();
    }

    public Page<ApartamentoDetails> findAllApartamento(Pageable pageable){
        return this.repository.findAllApartamento(pageable);
    }
}
