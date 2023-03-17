package com.api.redesdeprotecao.dto_outputs;

import com.api.redesdeprotecao.models.FoneModel;
import lombok.Data;

import java.util.UUID;

@Data
public class FoneDtoOutput {
    private UUID foneid;
    private Long foneNumero;
    private String FoneTipo;
}
