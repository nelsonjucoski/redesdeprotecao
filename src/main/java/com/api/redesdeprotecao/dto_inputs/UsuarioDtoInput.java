package com.api.redesdeprotecao.dto_inputs;

import com.api.redesdeprotecao.models.RoleModel;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class UsuarioDtoInput {

    private UUID userId;
    private String username;
    private String password;
    private String nome;
    private String email;
    private OffsetDateTime infoCadastramento;
    private OffsetDateTime infoAlteracao;
    private List<RoleModel> roles;
}
