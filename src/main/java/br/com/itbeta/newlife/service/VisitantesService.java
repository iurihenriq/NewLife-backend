package br.com.itbeta.newlife.service;

import br.com.itbeta.newlife.controller.form.VisitanteForm;
import br.com.itbeta.newlife.model.Visitante;
import br.com.itbeta.newlife.repository.ApartamentoRepository;
import br.com.itbeta.newlife.repository.VisitanteRepository;
import br.com.itbeta.newlife.repository.specifications.VisitanteSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class VisitantesService {
    private final VisitanteRepository repository;
    private final ApartamentoRepository apartamentoRepository;

    public VisitanteForm findById(Long idVisitante) {
        Visitante m = this.repository.findById(idVisitante).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new VisitanteForm(m);
    }

    public void createVisitante(VisitanteForm form) {
        Visitante v = Visitante
                .builder()
                .nome(form.getNome())
                .rg(form.getRg())
                .cpf(form.getCpf())
                .telefonePrincipal(form.getTelefonePrincipal())
                .telefoneSecundario(form.getTelefoneSecundario())
                .observacoes(form.getObservacoes())
                .build();
        v.addApartamentos(this.apartamentoRepository.findById(form.getApartamento()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        this.repository.save(v);
    }

    public void updateVisitante(Long idVisitante, VisitanteForm form) {
        Visitante v = this.repository.findById(idVisitante).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        v.addApartamentos(this.apartamentoRepository.findById(form.getApartamento()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        v.update(form);
        repository.save(v);
    }

    public void deleteVisitante(Long idVisitante) {
        Visitante v = this.repository.findById(idVisitante).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        repository.delete(v);
    }

    public Page<Visitante> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    public Page<Visitante> findAll(Pageable pageable, String query) {
        return this.repository.findAll(VisitanteSpecification.likeGenericQuery(query), pageable);
    }
}
