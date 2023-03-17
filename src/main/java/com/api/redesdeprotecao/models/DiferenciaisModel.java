package com.api.redesdeprotecao.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "diferenciais")
public class DiferenciaisModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "difId")
    private UUID difId;

    @Column(name = "difNome")
    private String difNome;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "anuncioId", referencedColumnName = "anuncioId", foreignKey = @ForeignKey(name = "fk_anuncio"))
    @JsonBackReference
    private AnuncioModel anuncioModel;

}
