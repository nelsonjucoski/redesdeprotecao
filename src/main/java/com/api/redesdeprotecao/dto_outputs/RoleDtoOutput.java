package com.api.redesdeprotecao.dto_outputs;

import com.api.redesdeprotecao.enums.RoleName;
import lombok.Data;

import java.util.UUID;

@Data
public class RoleDtoOutput {

    private UUID roleId;
    private RoleName roleName;
}
