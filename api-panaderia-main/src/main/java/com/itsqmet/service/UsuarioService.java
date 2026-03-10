package com.itsqmet.service;


import com.itsqmet.entity.Usuario;
import com.itsqmet.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> mostrarUsuario(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarUsuarioPorId(Long id){
        return usuarioRepository.findById(id);
    }

    public Usuario guardarUsuario (Usuario usuario){return  usuarioRepository.save(usuario);}

    public void eliminarUsuario (Long id){usuarioRepository.deleteById(id);}


    public Usuario login(String nombre, String password) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByNombre(nombre);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (passwordEncoder.matches(password, usuario.getPassword())) {
                return usuario;
            }
        }
        return null;
    }
}
