package br.com.itbeta.newlife.controller;

import br.com.itbeta.newlife.controller.dto.ApartamentoDto;
import br.com.itbeta.newlife.repository.projections.ApartamentoDetails;
import br.com.itbeta.newlife.service.ApartamentosService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping ("/apartamentos")
@AllArgsConstructor
public class ApartamentoController {

    private final ApartamentosService service;
    private final ConversionService conversionService;

    @GetMapping("/{idApartamento}")
    public ResponseEntity<?> getById (@PathVariable Long idApartamento) {
        ApartamentoDto dto = this.service.findById(idApartamento);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping("/allPaginetor")
    public @ResponseBody Page<ApartamentoDto> findAll(@RequestParam(required = false) String query, Pageable pageable){
        return this.service.findAllPaginated(pageable).map(entity-> this.conversionService.convert(entity, ApartamentoDto.class));
    }

    @GetMapping("/all")
    public @ResponseBody List<ApartamentoDetails> findAllPaginated(){
        return this.service.findAll();
    }

//    @PostMapping()
//    @Transactional
//    public ResponseEntity<?> createApartamento (@RequestBody FuncionarioForm form) {
//        this.service.createApartamento(form);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

//    @PutMapping("/{idFuncionario}")
//    @Transactional
//    public ResponseEntity<?> updateFuncionario (@PathVariable Long idFuncionario, @RequestBody FuncionarioForm form) {
//        this.service.updateFuncionario(idFuncionario, form);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{idFuncionario}")
//    public ResponseEntity<?> deleteFuncionario (@PathVariable Long idFuncionario) {
//        this.service.deleteFuncionario(idFuncionario);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}
