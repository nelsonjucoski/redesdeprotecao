package com.api.redesdeprotecao.dto_inputs;

import com.api.redesdeprotecao.enums.RoleName;
import lombok.Data;

import java.util.UUID;

@Data
public class RoleDtoInput {
    private UUID roleId;
    private RoleName roleName;
}
