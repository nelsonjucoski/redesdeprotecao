package com.api.redesdeprotecao.controllers;

import com.api.redesdeprotecao.dto_inputs.PagamentoDtoInput;
import com.api.redesdeprotecao.dto_outputs.PagamentoDtoOutput;
import com.api.redesdeprotecao.models.PagamentoModel;
import com.api.redesdeprotecao.services.PagamentoService;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@RestController
@RequestMapping(value = "/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @ApiOperation(value = "Salvar formas de pagamentos de anuncios")
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<PagamentoDtoOutput> cadastrar(@RequestBody PagamentoDtoInput pagamentoDtoInput) {
        PagamentoModel pagamentoModel = converteDtoInput(pagamentoDtoInput);
        pagamentoService.salvar(pagamentoModel);
        return new ResponseEntity<PagamentoDtoOutput>(converteDtoOutput(pagamentoModel), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Listar formas de pagamentos de anuncios")
    @GetMapping(value = "/")
    @ResponseBody
    public ResponseEntity<List<PagamentoDtoOutput>> listarPagamentos() {
        List<PagamentoModel> pagamentoModels = pagamentoService.listaPagamentos();
        List<PagamentoDtoOutput> pagamentoDtoOutputs = converteListaPagamentos(pagamentoModels);
        return new ResponseEntity<List<PagamentoDtoOutput>>(pagamentoDtoOutputs, HttpStatus.OK);
    }
    //TODO outros métodos talves sejam necessários.

    /*** Metodos de convessão de objetos ***/

    private PagamentoModel converteDtoInput(PagamentoDtoInput pagamentoDtoInput) {
        PagamentoModel pagamentoModel = new PagamentoModel();
        pagamentoModel.setPagId(pagamentoDtoInput.getPagID());
        pagamentoModel.setPagamentoEnum(pagamentoDtoInput.getPagamentoEnum());

        return pagamentoModel;
    }

    private PagamentoDtoOutput converteDtoOutput(PagamentoModel pagamentoModel) {
        PagamentoDtoOutput pagamentoDtoOutput = new PagamentoDtoOutput();
        pagamentoDtoOutput.setPagID(pagamentoModel.getPagId());
        pagamentoDtoOutput.setPagamentoEnum(pagamentoModel.getPagamentoEnum());

        return pagamentoDtoOutput;
    }

    private List<PagamentoDtoOutput> converteListaPagamentos(List<PagamentoModel> pagamentoModels) {
        return pagamentoModels.stream().map(PagamentoModel -> converteDtoOutput(PagamentoModel)).collect(Collectors.toList());
    }


}
