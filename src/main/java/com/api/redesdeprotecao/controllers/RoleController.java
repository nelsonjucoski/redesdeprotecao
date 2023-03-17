package com.api.redesdeprotecao.controllers;

import com.api.redesdeprotecao.dto_inputs.RoleDtoInput;
import com.api.redesdeprotecao.dto_outputs.RoleDtoOutput;
import com.api.redesdeprotecao.enums.RoleName;
import com.api.redesdeprotecao.models.RoleModel;
import com.api.redesdeprotecao.repositories.RoleRepository;
import com.api.redesdeprotecao.services.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Api(tags = "Roles")
@RestController
@RequestMapping(value = "/roles")
public class RoleController {

    private final RoleService roleService;
    private final RoleRepository roleRepository;

    public RoleController(RoleService roleService,
                          RoleRepository roleRepository) {
        this.roleService = roleService;
        this.roleRepository = roleRepository;
    }

    @ApiOperation(value = "Salvar Roles")
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<RoleDtoOutput> cadastrar(@RequestBody RoleDtoInput roleDtoInput) {
        RoleModel roleModel = converteDtoInput(roleDtoInput);
        roleService.salvar(roleModel);
        return new ResponseEntity<RoleDtoOutput>(converteDtoOutput(roleModel), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualizar Roles")
    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<RoleDtoOutput> atualizar(@RequestBody RoleDtoInput roleDtoInput) {
        RoleModel roleModel = converteDtoInput(roleDtoInput);
        roleService.salvar(roleModel);
        return new ResponseEntity<RoleDtoOutput>(converteDtoOutput(roleModel), HttpStatus.OK);
    }

    @ApiOperation(value = "Deletar Roles")
    @DeleteMapping(value = "/")
    public ResponseEntity<String> deletar(@RequestParam UUID roleId) {
        roleService.delete(roleId);
        return new ResponseEntity<String>("Role deletada com sucesso!!!", HttpStatus.OK);
    }

    @ApiOperation(value = "Listar Roles")
    @GetMapping(value = "/")
    @ResponseBody
    public ResponseEntity<List<RoleDtoOutput>> ListarRoles() {
        List<RoleModel> roleModels = roleService.listaRoles();
        List<RoleDtoOutput> roleDtoOutputs = converteListaRoles(roleModels);
        return new ResponseEntity<List<RoleDtoOutput>>(roleDtoOutputs, HttpStatus.OK);
    }

    @ApiOperation(value = "Busca Roles por Nome")
    @GetMapping(value = "/{role}")
    public ResponseEntity<RoleDtoOutput> buscaPorRoleName(@PathVariable(value = "role") RoleName role) {
        RoleDtoOutput roleDtoOutput = converteDtoOutput(roleRepository.findByRoleName(role));
        return new ResponseEntity<RoleDtoOutput>(roleDtoOutput, HttpStatus.OK);
    }
    //TODO outros métodos talves sejam necessários.

    /*** Metodos de convessão de objetos ***/
    private RoleModel converteDtoInput(RoleDtoInput roleDtoInput) {
        RoleModel roleModel = new RoleModel();
        roleModel.setRoleId(roleDtoInput.getRoleId());
        roleModel.setRoleName(roleDtoInput.getRoleName());

        return roleModel;
    }

    private RoleDtoOutput converteDtoOutput(RoleModel roleModel) {
        RoleDtoOutput roleDtoOutput = new RoleDtoOutput();
        roleDtoOutput.setRoleId(roleModel.getRoleId());
        roleDtoOutput.setRoleName(roleModel.getRoleName());

        return roleDtoOutput;
    }

    private List<RoleDtoOutput> converteListaRoles(List<RoleModel> roleModels) {
        return roleModels.stream().map(RoleModel -> converteDtoOutput(RoleModel)).collect(Collectors.toList());
    }
}
