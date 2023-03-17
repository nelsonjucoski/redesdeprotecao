package com.api.redesdeprotecao.services;

import com.api.redesdeprotecao.models.UsuarioModel;
import com.api.redesdeprotecao.repositories.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UsuarioService implements UserDetailsService {

    final
    UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioModel usuarioModel = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário: " + username + " não encontrado!"));
        return new User(usuarioModel.getUsername(), usuarioModel.getPassword(), true, true, true, true, usuarioModel.getAuthorities());

    }

    @Transactional
    public UsuarioModel salvar(UsuarioModel usuarioModel) {
        return usuarioRepository.save(usuarioModel);
    }

    @Transactional(readOnly = true)
    public List<UsuarioModel> listaUsuarios() {
        List<UsuarioModel> usuarioModels = usuarioRepository.findAll();

        return usuarioModels;
    }
}
