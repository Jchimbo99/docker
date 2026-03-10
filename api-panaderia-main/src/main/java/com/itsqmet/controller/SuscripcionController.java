package com.itsqmet.controller;


import com.itsqmet.entity.Suscripcion;
import com.itsqmet.service.SuscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("suscripciones")
@CrossOrigin(origins = "http://localhost:4200")
public class SuscripcionController {

    @Autowired
    private SuscripcionService suscripcionService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping
    public List<Suscripcion> leerSuscripcion(){
        List<Suscripcion> suscripcions = suscripcionService.mostrarSuscripcion();
        return suscripcions;

    }

    @GetMapping("/{id}")
    public Optional<Suscripcion> suscripcionPorID(@PathVariable Long id) {
        return suscripcionService.buscarSuscripcionPorId(id);
    }

    @PostMapping("/guardar")
    public Suscripcion guardar(@RequestBody Suscripcion suscripcion) {
        suscripcion.setPassword(passwordEncoder.encode(suscripcion.getPassword()));
        return suscripcionService.guardarSuscripcion(suscripcion);
    }

    @PutMapping("/editar/{id}")
    public Suscripcion actualizar(@PathVariable Long id, @RequestBody Suscripcion suscripcion) {
        Optional<Suscripcion> suscripcionBuscado = suscripcionService.buscarSuscripcionPorId(id);
        if(suscripcionBuscado.isPresent()){
            Suscripcion suscripcionExiste = suscripcionBuscado.get();
            suscripcionExiste.setNombre(suscripcion.getNombre());
            suscripcionExiste.setCorreo(suscripcion.getCorreo());
            suscripcionExiste.setTelefono(suscripcion.getTelefono());
            suscripcionExiste.setPassword(suscripcion.getPassword());
            suscripcionExiste.setConfirmarPassword(suscripcion.getConfirmarPassword());
            return suscripcionService.guardarSuscripcion(suscripcionExiste);
        }
        return null;
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id){
        suscripcionService.eliminarSuscripcion(id);
    }

}
