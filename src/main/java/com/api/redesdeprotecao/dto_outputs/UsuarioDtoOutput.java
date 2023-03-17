package com.api.redesdeprotecao.dto_outputs;

import com.api.redesdeprotecao.models.RoleModel;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class UsuarioDtoOutput {
    private UUID userId;
    private String username;
    private String password;
    private String nome;
    private String email;
    private OffsetDateTime infoCadastramento;
    private OffsetDateTime infoAlteracao;
    private List<RoleModel> roles;
}
