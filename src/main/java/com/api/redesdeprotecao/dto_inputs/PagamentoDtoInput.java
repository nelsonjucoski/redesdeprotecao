package com.api.redesdeprotecao.dto_inputs;

import com.api.redesdeprotecao.enums.PagamentoEnum;
import lombok.Data;

import java.util.UUID;

@Data
public class PagamentoDtoInput {

    private UUID PagID;
    private PagamentoEnum pagamentoEnum;
}
