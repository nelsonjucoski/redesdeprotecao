package com.api.redesdeprotecao.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "fones")
public class FoneModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "foneId")
    private UUID foneId;


    @Column(name = "foneNumero")
    private Long foneNumero;


    @Column(name = "foneTipo")
    private String foneTipo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "anuncioId", referencedColumnName = "anuncioId", foreignKey = @ForeignKey(name = "fk_anuncio"))
    @JsonBackReference
    private AnuncioModel anuncioModel;
}
