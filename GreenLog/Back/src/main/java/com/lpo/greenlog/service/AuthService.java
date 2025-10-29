package com.lpo.greenlog.service;

import com.lpo.greenlog.model.Usuario;
import com.lpo.greenlog.repository.UsuarioRepository;
import com.lpo.greenlog.utils.HashUtil;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean autenticar(String username, String senhaPura) {
        String senhaHash = HashUtil.hashSenha(senhaPura);
        
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            return usuario.getPassword().equals(senhaHash);
        } else {
            return false;
        }
    }

    public Usuario salvarUsuario(Usuario usuario) {
        usuario.setPassword(HashUtil.hashSenha(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }
}
