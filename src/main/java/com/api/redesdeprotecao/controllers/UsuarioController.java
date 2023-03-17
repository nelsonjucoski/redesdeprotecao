package com.api.redesdeprotecao.controllers;

import com.api.redesdeprotecao.dto_inputs.UsuarioDtoInput;
import com.api.redesdeprotecao.dto_outputs.UsuarioDtoOutput;
import com.api.redesdeprotecao.enums.RoleName;
import com.api.redesdeprotecao.models.RoleModel;
import com.api.redesdeprotecao.models.UsuarioModel;
import com.api.redesdeprotecao.repositories.RoleRepository;
import com.api.redesdeprotecao.repositories.UsuarioRepository;
import com.api.redesdeprotecao.services.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Api(tags = "Usuários")
@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;

    public UsuarioController(UsuarioService usuarioService,
                             UsuarioRepository usuarioRepository,
                             RoleRepository roleRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
    }

    @ApiOperation(value = "Salvar usuário")
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<UsuarioDtoOutput> cadastrar(@RequestBody UsuarioDtoInput usuarioDtoInput) {
        UsuarioModel usuarioModel = converteDtoInput(usuarioDtoInput);
        RoleModel roleModel = roleRepository.findByRoleName(RoleName.valueOf("ROLE_USER"));
        usuarioModel.setRoles(Collections.singletonList(roleModel));
        usuarioService.salvar(usuarioModel);
        return new ResponseEntity<UsuarioDtoOutput>(converteDtoOutput(usuarioModel), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Listar usuário")
    @GetMapping(value = "/", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<UsuarioDtoOutput>> listaUsuarios() {
        List<UsuarioModel> usuarioModels = usuarioService.listaUsuarios();
        List<UsuarioDtoOutput> usuarioDtoOutputs = convertListaUsuario(usuarioModels);
        return new ResponseEntity<List<UsuarioDtoOutput>>(usuarioDtoOutputs, HttpStatus.OK);
    }

    /**
     * Método para criptografar senhas
     */
    @Autowired
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * Métodos de conversão de objetos
     **/

    private UsuarioDtoOutput converteDtoOutput(UsuarioModel usuarioModel) {
        UsuarioDtoOutput usuarioDtoOutput = new UsuarioDtoOutput();
        usuarioDtoOutput.setUserId(usuarioModel.getUserId());
        usuarioDtoOutput.setUsername(usuarioModel.getUsername());
        usuarioDtoOutput.setPassword(usuarioModel.getPassword());//TODO
        usuarioDtoOutput.setNome(usuarioModel.getNome());
        usuarioDtoOutput.setEmail(usuarioModel.getEmail());
        usuarioDtoOutput.setInfoCadastramento(usuarioModel.getInfoCadastramento());
        usuarioDtoOutput.setInfoAlteracao(usuarioModel.getInfoAlteracao());
        //usuarioDtoOutput.setRoles(usuarioModel.getRoles()); //TODO

        return usuarioDtoOutput;
    }

    private UsuarioModel converteDtoInput(UsuarioDtoInput usuarioDtoInput) {
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setUserId(usuarioDtoInput.getUserId());
        usuarioModel.setUsername(usuarioDtoInput.getUsername());
        usuarioModel.setPassword(passwordEncoder().encode(usuarioDtoInput.getPassword()));
        usuarioModel.setNome(usuarioDtoInput.getNome());
        usuarioModel.setEmail(usuarioDtoInput.getEmail());
        usuarioModel.setInfoCadastramento(usuarioDtoInput.getInfoCadastramento());
        usuarioModel.setInfoAlteracao(usuarioDtoInput.getInfoAlteracao());
        //usuarioModel.setRoles(usuarioDtoInput.getRoles()); //TODO

        return usuarioModel;
    }

    private List<UsuarioDtoOutput> convertListaUsuario(List<UsuarioModel> usuarioModels) {
        return usuarioModels.stream().map(usuarioModel -> converteDtoOutput(usuarioModel)).collect(Collectors.toList());
    }


}
