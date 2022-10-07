package br.com.itbeta.newlife.service;

import br.com.itbeta.newlife.controller.form.FuncionarioForm;
import br.com.itbeta.newlife.model.Funcionario;
import br.com.itbeta.newlife.repository.ApartamentoRepository;
import br.com.itbeta.newlife.repository.FuncionarioRepository;
import br.com.itbeta.newlife.repository.specifications.FuncionarioSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class FuncionariosService {

    private final FuncionarioRepository repository;

    private final ApartamentoRepository apartamentoRepository;

    public FuncionarioForm findById(Long idFuncionario) {
        Funcionario f = this.repository.findById(idFuncionario).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new FuncionarioForm(f);
    }

    public void createFuncionario(FuncionarioForm form) {
        Funcionario f = Funcionario
                .builder()
                .nome(form.getNome())
                .rg(form.getRg())
                .cpf(form.getCpf())
                .telefonePrincipal(form.getTelefonePrincipal())
                .telefoneSecundario(form.getTelefoneSecundario())
                .observacoes(form.getObservacoes())
                .build();
        f.addApartamentos(this.apartamentoRepository.findById(form.getApartamento()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        this.repository.save(f);
    }

    public void updateFuncionario(Long idFuncionario, FuncionarioForm form) {
        Funcionario f = this.repository.findById(idFuncionario).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        f.addApartamentos(this.apartamentoRepository.findById(form.getApartamento()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        f.update(form);
        repository.save(f);
    }

    public void deleteFuncionario(Long idFuncionario) {
        Funcionario f = this.repository.findById(idFuncionario).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        repository.delete(f);
    }

    public Page<Funcionario> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    public Page<Funcionario> findAll(Pageable pageable, String query) {
        return this.repository.findAll(FuncionarioSpecification.likeGenericQuery(query), pageable);
    }

}
