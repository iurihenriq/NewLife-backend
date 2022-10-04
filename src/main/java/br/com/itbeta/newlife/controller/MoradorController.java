package br.com.itbeta.newlife.controller;

import br.com.itbeta.newlife.controller.dto.FuncionarioDto;
import br.com.itbeta.newlife.controller.dto.MoradorDto;
import br.com.itbeta.newlife.controller.form.FuncionarioForm;
import br.com.itbeta.newlife.controller.form.MoradorForm;
import br.com.itbeta.newlife.repository.projections.MoradorDetails;
import br.com.itbeta.newlife.service.FuncionariosService;
import br.com.itbeta.newlife.service.MoradoresService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping ("/moradores")
@AllArgsConstructor
public class MoradorController {

    private final MoradoresService service;
    private final ConversionService conversionService;

    @GetMapping("/{idMorador}")
    public ResponseEntity<?> getById (@PathVariable Long idMorador) {
        MoradorForm form = this.service.findById(idMorador);
        return ResponseEntity.status(HttpStatus.OK).body(form);
    }

    @GetMapping("/all")
    public @ResponseBody
    Page<MoradorDto> findAllPaginated(@RequestParam(required = false) String query, Pageable pageable) {
        if(query == null) {
            return this.service.findAll(pageable).map(entity -> this.conversionService.convert(entity, MoradorDto.class));
        }
        return this.service.findAll(pageable, query).map(entity -> this.conversionService.convert(entity, MoradorDto.class));
    }

    @GetMapping("/allList")
    public @ResponseBody List<MoradorDetails> findAllList(@RequestParam(required = false) String query){
        if(query==null){return this.service.findAllList();}
        return this.service.findAllList(query);
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<?> createMorador (@RequestBody MoradorForm form) {
        this.service.createMorador(form);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{idMorador}")
    @Transactional
    public ResponseEntity<?> updateMorador (@PathVariable Long idMorador, @RequestBody MoradorForm form) {
        this.service.updateMorador(idMorador, form);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{idMorador}")
    public ResponseEntity<?> deleteMorador (@PathVariable Long idMorador) {
        this.service.deleteMorador(idMorador);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
