package br.com.itbeta.newlife.controller;

import br.com.itbeta.newlife.controller.dto.FuncionarioDto;
import br.com.itbeta.newlife.controller.dto.VeiculoDto;
import br.com.itbeta.newlife.controller.form.FuncionarioForm;
import br.com.itbeta.newlife.controller.form.VeiculoForm;
import br.com.itbeta.newlife.service.FuncionariosService;
import br.com.itbeta.newlife.service.VeiculosService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping ("/veiculos")
@AllArgsConstructor
public class VeiculoController {
    private final VeiculosService service;
    private final ConversionService conversionService;

    @GetMapping("/{idVeiculo}")
    public ResponseEntity<?> getById (@PathVariable Long idVeiculo) {
        VeiculoForm form = this.service.findById(idVeiculo);
        return ResponseEntity.status(HttpStatus.OK).body(form);
    }

    @GetMapping("/all")
    public @ResponseBody
    Page<VeiculoDto> findAllPaginated(@RequestParam(required = false) String query, Pageable pageable) {
        if(query == null) {
            return this.service.findAll(pageable).map(entity -> this.conversionService.convert(entity, VeiculoDto.class));
        }
        return this.service.findAll(pageable, query).map(entity -> this.conversionService.convert(entity, VeiculoDto.class));
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<?> createVeiculo (@RequestBody VeiculoForm form) {
        this.service.createVeiculo(form);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{idVeiculo}")
    @Transactional
    public ResponseEntity<?> updateVeiculo (@PathVariable Long idVeiculo, @RequestBody VeiculoForm form) {
        this.service.updateVeiculo(idVeiculo, form);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{idVeiculo}")
    public ResponseEntity<?> deleteVeiculo (@PathVariable Long idVeiculo) {
        this.service.deleteVeiculo(idVeiculo);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
