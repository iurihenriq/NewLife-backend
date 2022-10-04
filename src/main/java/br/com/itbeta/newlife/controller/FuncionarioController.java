package br.com.itbeta.newlife.controller;

import br.com.itbeta.newlife.controller.dto.FuncionarioDto;
import br.com.itbeta.newlife.controller.form.FuncionarioForm;
import br.com.itbeta.newlife.service.FuncionariosService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping ("/funcionarios")
@AllArgsConstructor
public class FuncionarioController {

    private final FuncionariosService service;
    private final ConversionService conversionService;

    @GetMapping("/{idFuncionario}")
    public ResponseEntity<?> getById (@PathVariable Long idFuncionario) {
        FuncionarioForm form = this.service.findById(idFuncionario);
        return ResponseEntity.status(HttpStatus.OK).body(form);
    }

    @GetMapping("/all")
    public @ResponseBody
    Page<FuncionarioDto> findAllPaginated(@RequestParam(required = false) String query, Pageable pageable) {
        if(query == null) {
            return this.service.findAll(pageable).map(entity -> this.conversionService.convert(entity, FuncionarioDto.class));
        }
        return this.service.findAll(pageable, query).map(entity -> this.conversionService.convert(entity, FuncionarioDto.class));
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<?> createFuncionario (@RequestBody FuncionarioForm form) {
        this.service.createFuncionario(form);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{idFuncionario}")
    @Transactional
    public ResponseEntity<?> updateFuncionario (@PathVariable Long idFuncionario, @RequestBody FuncionarioForm form) {
        this.service.updateFuncionario(idFuncionario, form);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{idFuncionario}")
    public ResponseEntity<?> deleteFuncionario (@PathVariable Long idFuncionario) {
        this.service.deleteFuncionario(idFuncionario);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
