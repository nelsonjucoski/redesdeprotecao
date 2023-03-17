package com.api.redesdeprotecao.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "RedesSociais")
public class RedesSociaisModel implements Serializable {
    private static  final long serialVersion = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "redeId")
    private UUID redeId;

    @Column(name = "redeUrl")
    private String redeUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "anuncioId", referencedColumnName = "anuncioId", foreignKey = @ForeignKey(name = "fk_anuncio"))
    @JsonBackReference
    private AnuncioModel anuncioModel;
}
