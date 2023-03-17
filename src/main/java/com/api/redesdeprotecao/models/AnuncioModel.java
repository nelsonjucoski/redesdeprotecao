package com.api.redesdeprotecao.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "anuncio")
public class AnuncioModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "anuncioId")
    private UUID anuncioId;

    @Column(name = "anuncioDoc", nullable = false, unique = true)
    private Long anuncioDoc;

    @Column(name = "anuncioNome", nullable = false, length = 80)
    private String anuncioNome;

    @Column(name = "anuncioLogradouro", nullable = false)
    private String anuncioLogradouro;

    @Column(name = "anuncioLogrNum")
    private String anuncioLogrNum;

    @Column(name = "anuncioBairro", nullable = false)
    private String anuncioBairro;

    @Column(name = "anuncioMunicipio", nullable = false)
    private String anuncioMunicipio;

    @Column(name = "anuncioUf", nullable = false, length = 2)
    private String anuncioUf;

    @Column(name = "anuncioEmail", unique = true)
    private String anuncioEmail;

    @Column(name = "anuncioSite")
    private String anuncioSite;

    @Column(name = "anuncioSobre")
    private String anuncioSobre; //TODO

    @Column(name = "anuncioLogo")
    private String anuncioLogo; //TODO

    @OneToMany(mappedBy = "anuncioModel", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<FoneModel> anuncioFones = new ArrayList<FoneModel>();

    @OneToMany(mappedBy = "anuncioModel", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<RedesSociaisModel> anuncioRedesSociais = new ArrayList<RedesSociaisModel>();

    @OneToMany(mappedBy = "anuncioModel", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<DiferenciaisModel> anuncioDiferenciais = new ArrayList<DiferenciaisModel>();

    @OneToMany(mappedBy = "anuncioModel", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<RegiaoModel> anuncioRegiao = new ArrayList<RegiaoModel>();

    /*@ManyToMany
    @JoinTable(name = "anuncioPagamentos",
    joinColumns = @JoinColumn(name = "anuncio_id"),
    inverseJoinColumns = @JoinColumn(name = "pag_id"))
    private List<PagamentoModel> pagamentoModels;*/
}
