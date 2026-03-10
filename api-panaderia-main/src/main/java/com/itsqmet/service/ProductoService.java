package com.itsqmet.service;

import com.itsqmet.entity.Producto;
import com.itsqmet.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> mostrar(){
        return productoRepository.findAll();
    }

    public Optional<Producto> buscarProductoPorId(Long id){
        return productoRepository.findById(id);
    }

    public Producto guardarProducto (Producto producto){
        return  productoRepository.save(producto);
    }

    public void eliminarProducto (Long id){
        productoRepository.deleteById(id);
    }

}
