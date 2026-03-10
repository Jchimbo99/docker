package com.itsqmet.service;


import com.itsqmet.entity.Suscripcion;
import com.itsqmet.repository.SuscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuscripcionService {

    @Autowired
    private SuscripcionRepository suscripcionRepository;

    public List<Suscripcion> mostrarSuscripcion(){
        return suscripcionRepository.findAll();
    }

    public Optional<Suscripcion> buscarSuscripcionPorId(Long id){
        return suscripcionRepository.findById(id);
    }

    public Suscripcion guardarSuscripcion (Suscripcion suscripcion){return  suscripcionRepository.save(suscripcion);}

    public void eliminarSuscripcion (Long id){suscripcionRepository.deleteById(id);}
}
