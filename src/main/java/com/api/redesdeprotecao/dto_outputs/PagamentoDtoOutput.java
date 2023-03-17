package com.api.redesdeprotecao.dto_outputs;

import com.api.redesdeprotecao.enums.PagamentoEnum;
import lombok.Data;

import java.util.UUID;

@Data
public class PagamentoDtoOutput {

    private UUID PagID;
    private PagamentoEnum pagamentoEnum;
}
