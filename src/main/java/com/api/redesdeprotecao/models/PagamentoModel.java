package com.api.redesdeprotecao.models;

import com.api.redesdeprotecao.enums.PagamentoEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "pagamento")
public class PagamentoModel implements Serializable {
    private static final long serialVersion = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PagId")
    private UUID pagId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "pagNome", nullable = false, unique = true)
    private PagamentoEnum pagamentoEnum;


}
