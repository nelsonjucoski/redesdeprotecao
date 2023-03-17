package com.api.redesdeprotecao.dto_outputs;

import com.api.redesdeprotecao.models.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class AnuncioDtoOutput {
    private UUID anuncioId;
    private Long anuncioDoc;
    private String anuncioNome;
    private String anuncioLogradouro;
    private String anuncioLogrNum;
    private String anuncioBairro;
    private String anuncioMunicipio;
    private String anuncioUf;
    private String anuncioEmail;
    private String anuncioSite;
    private String anuncioSobre;
    private String anuncioLogo;
    private List<FoneModel> anuncioFones;
    private List<RedesSociaisModel> anuncioRedesSociais;
    private List<DiferenciaisModel> anuncioDiferenciais;
    private List<RegiaoModel> anuncioRegiao;

    //private List<PagamentoModel> pagamentoModels;

}
