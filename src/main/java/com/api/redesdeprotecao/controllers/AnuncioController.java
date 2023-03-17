package com.api.redesdeprotecao.controllers;

import com.api.redesdeprotecao.dto_inputs.AnuncioDtoInput;
import com.api.redesdeprotecao.dto_outputs.AnuncioDtoOutput;
import com.api.redesdeprotecao.models.*;
import com.api.redesdeprotecao.repositories.AnuncioRepository;
import com.api.redesdeprotecao.services.AnuncioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Api(tags = "Anuncios")
@RestController
@RequestMapping(value = "/anuncios")
public class AnuncioController {

    private final AnuncioService anuncioService;
    private final AnuncioRepository anuncioRepository;

    public AnuncioController(AnuncioService anuncioService,
                             AnuncioRepository anuncioRepository) {
        this.anuncioService = anuncioService;
        this.anuncioRepository = anuncioRepository;
    }

    @ApiOperation(value = "Salvar anúncios")
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<AnuncioDtoOutput> cadastrar(@RequestBody AnuncioDtoInput anuncioDtoInput) {
        AnuncioModel anuncioModel = converteDtoInput(anuncioDtoInput);
        anuncioService.salvar(anuncioModel);
        return new ResponseEntity<AnuncioDtoOutput>(converteDtoOutput(anuncioModel), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualizar anúncio")
    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<AnuncioDtoOutput> atualizar(@RequestBody AnuncioDtoInput anuncioDtoInput) {
        AnuncioModel anuncioModel = converteDtoInput(anuncioDtoInput);
        anuncioService.salvar(anuncioModel);
        return new ResponseEntity<AnuncioDtoOutput>(converteDtoOutput(anuncioModel), HttpStatus.OK);
    }

    @ApiOperation(value = "Deletar Anúncio")
    @DeleteMapping(value = "/")
    @ResponseBody
    public ResponseEntity<String> deletar(@RequestParam UUID idAnuncio) {
        anuncioService.deletar(idAnuncio);
        return new ResponseEntity<String>("Anúncio deletado com sucesso!!!", HttpStatus.OK);
    }

    @ApiOperation(value = "Listar todos os anúncios")
    @GetMapping(value = "/", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<AnuncioDtoOutput>> listarAnuncios() {
        List<AnuncioModel> anuncioModels = anuncioService.listarAnuncios();
        List<AnuncioDtoOutput> anuncioDtoOutputs = converteListaAnuncios(anuncioModels);
        return new ResponseEntity<List<AnuncioDtoOutput>>(anuncioDtoOutputs, HttpStatus.OK);
    }

    @ApiOperation(value = "Buscar anúncios por ID")
    @GetMapping(value = "/{idAnuncio}", produces = "application/json")
    public ResponseEntity<AnuncioDtoOutput> buscaAnuncioPorId(@PathVariable(value = "idAnuncio") UUID idAnuncio) {
        AnuncioModel anuncioModel = anuncioService.buscaAnuncioPorId(idAnuncio);
        AnuncioDtoOutput anuncioDtoOutput = converteDtoOutput(anuncioModel);
        return new ResponseEntity<AnuncioDtoOutput>(anuncioDtoOutput, HttpStatus.OK);
    }


    /**
     * Métodos de conversão de objetos
     **/
    private AnuncioDtoOutput converteDtoOutput(AnuncioModel anuncioModel) {
        AnuncioDtoOutput anuncioDtoOutput = new AnuncioDtoOutput();

        anuncioDtoOutput.setAnuncioId(anuncioModel.getAnuncioId());
        anuncioDtoOutput.setAnuncioDoc(anuncioModel.getAnuncioDoc());
        anuncioDtoOutput.setAnuncioNome(anuncioModel.getAnuncioNome());
        anuncioDtoOutput.setAnuncioLogradouro(anuncioModel.getAnuncioLogradouro());
        anuncioDtoOutput.setAnuncioLogrNum(anuncioModel.getAnuncioLogrNum());
        anuncioDtoOutput.setAnuncioBairro(anuncioModel.getAnuncioBairro());
        anuncioDtoOutput.setAnuncioMunicipio(anuncioModel.getAnuncioMunicipio());
        anuncioDtoOutput.setAnuncioUf(anuncioModel.getAnuncioUf());
        anuncioDtoOutput.setAnuncioEmail(anuncioModel.getAnuncioEmail());
        anuncioDtoOutput.setAnuncioSite(anuncioModel.getAnuncioSite());
        anuncioDtoOutput.setAnuncioSobre(anuncioModel.getAnuncioSobre());
        anuncioDtoOutput.setAnuncioLogo(anuncioModel.getAnuncioLogo());
        anuncioDtoOutput.setAnuncioFones(anuncioModel.getAnuncioFones());
        anuncioDtoOutput.setAnuncioRedesSociais(anuncioModel.getAnuncioRedesSociais());
        anuncioDtoOutput.setAnuncioDiferenciais(anuncioModel.getAnuncioDiferenciais());
        anuncioDtoOutput.setAnuncioRegiao(anuncioModel.getAnuncioRegiao());
        //anuncioDtoOutput.setPagamentoModels(anuncioModel.getPagamentoModels());

        return anuncioDtoOutput;
    }

    private AnuncioModel converteDtoInput(AnuncioDtoInput anuncioDtoInput) {
        AnuncioModel anuncioModel = new AnuncioModel();

        anuncioModel.setAnuncioId(anuncioDtoInput.getAnuncioId());
        anuncioModel.setAnuncioDoc(anuncioDtoInput.getAnuncioDoc());
        anuncioModel.setAnuncioNome(anuncioDtoInput.getAnuncioNome());
        anuncioModel.setAnuncioLogradouro(anuncioDtoInput.getAnuncioLogradouro());
        anuncioModel.setAnuncioLogrNum(anuncioDtoInput.getAnuncioLogrNum());
        anuncioModel.setAnuncioBairro(anuncioDtoInput.getAnuncioBairro());
        anuncioModel.setAnuncioMunicipio(anuncioDtoInput.getAnuncioMunicipio());
        anuncioModel.setAnuncioUf(anuncioDtoInput.getAnuncioUf());
        anuncioModel.setAnuncioEmail(anuncioDtoInput.getAnuncioEmail());
        anuncioModel.setAnuncioSite(anuncioDtoInput.getAnuncioSite());
        anuncioModel.setAnuncioSobre(anuncioDtoInput.getAnuncioSobre());
        anuncioModel.setAnuncioLogo(anuncioDtoInput.getAnuncioLogo());

        for (int i = 0; i < anuncioDtoInput.getAnuncioFones().size(); i++) {
            FoneModel foneModel = new FoneModel();
            foneModel.setFoneId(anuncioDtoInput.getAnuncioFones().get(i).getFoneId());
            foneModel.setFoneNumero(anuncioDtoInput.getAnuncioFones().get(i).getFoneNumero());
            foneModel.setFoneTipo(anuncioDtoInput.getAnuncioFones().get(i).getFoneTipo());
            foneModel.setAnuncioModel(anuncioModel);

            anuncioModel.getAnuncioFones().add(foneModel);
        }
        for (int i = 0; i < anuncioDtoInput.getAnuncioRedesSociais().size(); i++) {
            RedesSociaisModel redesSociaisModel = new RedesSociaisModel();
            redesSociaisModel.setRedeId(anuncioDtoInput.getAnuncioRedesSociais().get(i).getRedeId());
            redesSociaisModel.setRedeUrl(anuncioDtoInput.getAnuncioRedesSociais().get(i).getRedeUrl());
            redesSociaisModel.setAnuncioModel(anuncioModel);

            anuncioModel.getAnuncioRedesSociais().add(redesSociaisModel);
        }
        for (int i = 0; i < anuncioDtoInput.getAnuncioDiferenciais().size(); i++) {
            DiferenciaisModel diferenciaisModel = new DiferenciaisModel();
            diferenciaisModel.setDifId(anuncioDtoInput.getAnuncioDiferenciais().get(i).getDifId());
            diferenciaisModel.setDifNome(anuncioDtoInput.getAnuncioDiferenciais().get(i).getDifNome());
            diferenciaisModel.setAnuncioModel(anuncioModel);

            anuncioModel.getAnuncioDiferenciais().add(diferenciaisModel);
        }
        for (int i = 0; i < anuncioDtoInput.getAnuncioRegiao().size(); i++) {
            RegiaoModel regiaoModel = new RegiaoModel();
            regiaoModel.setRegiaoId(anuncioDtoInput.getAnuncioRegiao().get(i).getRegiaoId());
            regiaoModel.setRegiaoNome(anuncioDtoInput.getAnuncioRegiao().get(i).getRegiaoNome());
            regiaoModel.setAnuncioModel(anuncioModel);

            anuncioModel.getAnuncioRegiao().add(regiaoModel);
        }

        //anuncioModel.setPagamentoModels(anuncioDtoInput.getPagamentoModels());

        return anuncioModel;
    }

    private List<AnuncioDtoOutput> converteListaAnuncios(List<AnuncioModel> anuncioModels) {
        return anuncioModels.stream().map(anuncioModel -> converteDtoOutput(anuncioModel)).collect(Collectors.toList());
    }


}
