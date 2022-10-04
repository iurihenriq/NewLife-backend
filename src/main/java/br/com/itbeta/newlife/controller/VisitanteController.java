package br.com.itbeta.newlife.controller;

import br.com.itbeta.newlife.controller.dto.FuncionarioDto;
import br.com.itbeta.newlife.controller.dto.VisitanteDto;
import br.com.itbeta.newlife.controller.form.FuncionarioForm;
import br.com.itbeta.newlife.controller.form.VisitanteForm;
import br.com.itbeta.newlife.service.FuncionariosService;
import br.com.itbeta.newlife.service.VisitantesService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping ("/visitantes")
@AllArgsConstructor
public class VisitanteController {

    private final VisitantesService service;
    private final ConversionService conversionService;

    @GetMapping("/{idVisitante}")
    public ResponseEntity<?> getById (@PathVariable Long idVisitante) {
        VisitanteForm form = this.service.findById(idVisitante);
        return ResponseEntity.status(HttpStatus.OK).body(form);
    }

    @GetMapping("/all")
    public @ResponseBody
    Page<VisitanteDto> findAllPaginated(@RequestParam(required = false) String query, Pageable pageable) {
        if(query == null) {
            return this.service.findAll(pageable).map(entity -> this.conversionService.convert(entity, VisitanteDto.class));
        }
        return this.service.findAll(pageable, query).map(entity -> this.conversionService.convert(entity, VisitanteDto.class));
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<?> createVisitante (@RequestBody VisitanteForm form) {
        this.service.createVisitante(form);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{idVisitante}")
    @Transactional
    public ResponseEntity<?> updateVisitante (@PathVariable Long idVisitante, @RequestBody VisitanteForm form) {
        this.service.updateVisitante(idVisitante, form);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{idVisitante}")
    public ResponseEntity<?> deleteVisitante (@PathVariable Long idVisitante) {
        this.service.deleteVisitante(idVisitante);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
