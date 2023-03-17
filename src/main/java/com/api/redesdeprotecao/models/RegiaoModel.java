package com.api.redesdeprotecao.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "Regiao")
public class RegiaoModel implements Serializable {
    private static final long serialVersonUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "regiaoId")
    private UUID regiaoId;

    @Column(name = "regiaoNome", nullable = false)
    private  String regiaoNome;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "anuncioId" , referencedColumnName = "anuncioId", foreignKey = @ForeignKey(name = "fk_anuncio"))
    @JsonBackReference
    private AnuncioModel anuncioModel;

}
