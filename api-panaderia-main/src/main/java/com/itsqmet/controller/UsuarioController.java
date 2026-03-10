package com.itsqmet.controller;


import com.itsqmet.entity.LoginRequest;
import com.itsqmet.entity.Usuario;
import com.itsqmet.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping
    public List<Usuario> leerUsuario(){
        List<Usuario> usuarios = usuarioService.mostrarUsuario();
        return usuarios;

    }

    @GetMapping("/{id}")
    public Optional<Usuario> usuarioPorID(@PathVariable Long id) {
        return usuarioService.buscarUsuarioPorId(id);
    }


    @PostMapping("/guardar")
    public Usuario guardar(@RequestBody Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioService.guardarUsuario(usuario);
    }



    @PutMapping("/editar/{id}")
    public Usuario actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioBuscado = usuarioService.buscarUsuarioPorId(id);
        if(usuarioBuscado.isPresent()){
            Usuario usuarioExiste = usuarioBuscado.get();
            usuarioExiste.setNombre(usuario.getNombre());
            usuarioExiste.setDireccion(usuario.getDireccion());
            usuarioExiste.setFechaNacimiento(usuario.getFechaNacimiento());
            usuarioExiste.setCorreo(usuario.getCorreo());
            usuarioExiste.setPassword(usuario.getPassword());
            return usuarioService.guardarUsuario(usuarioExiste);
        }
        return null;
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id){
        usuarioService.eliminarUsuario(id);
    }


    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody LoginRequest request) {
        Usuario usuario = usuarioService.login(request.getUsuario(), request.getPassword());
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
