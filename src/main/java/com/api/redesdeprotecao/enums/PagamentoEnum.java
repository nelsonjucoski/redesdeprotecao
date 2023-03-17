package com.api.redesdeprotecao.enums;

public enum PagamentoEnum {

    /**
     * Não altere a ordem das constrants
     */
    DINHEIRO(1),
    PIX(2),
    CHEQUE(3),
    BOLETO(4),
    CARTAO_CREDITO(5),
    CARTAO_DEBITO(6),
    TED(7),
    DOC(8),
    LINK_PAGAMENTO(9);


    private final int code;

    PagamentoEnum(int code) {
        this.code = code;
    }
}
