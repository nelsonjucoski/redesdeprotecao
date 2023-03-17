package com.api.redesdeprotecao.services;

import com.api.redesdeprotecao.models.RoleModel;
import com.api.redesdeprotecao.models.UsuarioModel;
import com.api.redesdeprotecao.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleModel salvar(RoleModel roleModel) {
        return roleRepository.save(roleModel);
    }

    public List<RoleModel> listaRoles() {
        return roleRepository.findAll();
    }

    public void delete(UUID roleId) {
        roleRepository.deleteById(roleId);
    }




}
