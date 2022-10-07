package br.com.itbeta.newlife.service;

import br.com.itbeta.newlife.controller.dto.MoradorRow;
import br.com.itbeta.newlife.exception.SheetImportException;
import br.com.itbeta.newlife.model.Apartamento;
import br.com.itbeta.newlife.model.Morador;
import br.com.itbeta.newlife.repository.ApartamentoRepository;
import br.com.itbeta.newlife.repository.MoradorRepository;
import lombok.AllArgsConstructor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ImportService {

    private final MoradorRepository moradorRepository;
    private ApartamentoRepository apartamentoRepository;
    private List<Apartamento> apartamentoList = new ArrayList<>();

    public void importSheet(
            InputStream inputStream
    ) throws IOException,
            SAXException,
            OpenXML4JException {
        List<MoradorRow> moradorRows = this.parseSheet(inputStream);
        this.registerMoradores(moradorRows);
    }

    public List<MoradorRow> parseSheet(InputStream inputStream) throws IOException, SAXException, OpenXML4JException {
        this.apartamentoList = this.apartamentoRepository.findAll();
        OPCPackage pkg = OPCPackage.open(inputStream);
        SheetParser<List<MoradorRow>> sheetParser = new SheetParser<>(pkg, 10, rowInfo -> {
            String[] row = rowInfo.row;
            ArrayList<MoradorRow> rows = new ArrayList<>();
            MoradorRow moradorRow = MoradorRow
                    .builder()
                    .rowNumber(rowInfo.rowNumber)
                    .apartamento(this.getApto(row[0], rowInfo.rowNumber))
                    .nome(row[1])
                    .rg(row[2])
                    .cpf(row[3])
                    .email(row[4])
                    .telefonePrincipal(row[5])
                    .telefoneSecundario(row[6])
                    .nomeContatoEmergencial(row[7])
                    .telefoneContatoEmergencial(row[8])
                    .observacoes(row[9])
                    .build();
            rows.add(moradorRow);
            return rows;
        });

//        Map<Boolean, List<MoradorRow>> rows = sheetParser
//                .process()
//                .flatMap(Collection::stream)
//                .collect(Collectors.groupingBy(row -> row.hasError));
//        List<MoradorRow> wrongRows = rows.get(true);
//        List<MoradorRow> rightRows = rows.get(false);
//        return rightRows;

        List<MoradorRow> rows = sheetParser.process().flatMap(Collection::stream).collect(Collectors.toList());
        return rows;
    }


    public void registerMoradores(List<MoradorRow> moradorRows) {

        int moradoresSize = moradorRows.size();

        ArrayList<Morador> allMoradores = new ArrayList<>(moradoresSize);

        for (MoradorRow moradorRow : moradorRows) {
            Morador morador = Morador.builder()
                    .apartamento(moradorRow.apartamento)
                    .nome(moradorRow.nome)
                    .rg(moradorRow.rg)
                    .cpf(moradorRow.cpf)
                    .telefonePrincipal(moradorRow.telefonePrincipal)
                    .telefoneSecundario(moradorRow.telefoneSecundario)
                    .email(moradorRow.email)
                    .nomeContatoEmergencial(moradorRow.nomeContatoEmergencial)
                    .telefoneContatoEmergencial(moradorRow.telefoneContatoEmergencial)
                    .observacoes(moradorRow.observacoes)
                    .build();
            allMoradores.add(morador);
        }
        this.moradorRepository.saveAll(allMoradores);

    }

    public Apartamento getApto(String numeroApto, int rowNumber) {
        for (Apartamento apartamento : this.apartamentoList) {
            if (numeroApto != null)
                if (apartamento.getNumero() == Long.parseLong(numeroApto)) {
                    return apartamento;
                } else {
                    throw new SheetImportException("O apartamento não existe! Falha na linha: " + (rowNumber + 1));
                }
        }
        throw new SheetImportException("O campo Apartamento deve ser preenchido! Falha na linha: " + (rowNumber + 1));
    }
}