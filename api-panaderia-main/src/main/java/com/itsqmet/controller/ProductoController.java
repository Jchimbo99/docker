package com.itsqmet.controller;

import com.itsqmet.entity.Producto;
import com.itsqmet.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("productos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> leerProducto(){
        List<Producto> productos = productoService.mostrar();
        return productos;

    }

    @GetMapping("/{id}")
    public Optional<Producto> productoPorID(@PathVariable Long id) {
        return productoService.buscarProductoPorId(id);
    }

    @PostMapping("/guardar")
    public Producto guardar(@RequestBody Producto producto) {
        return productoService.guardarProducto(producto);
    }

    @PutMapping("/editar/{id}")
    public Producto actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        Optional<Producto> productoBuscado = productoService.buscarProductoPorId(id);
        if(productoBuscado.isPresent()){
            Producto productoExiste = productoBuscado.get();
            productoExiste.setNombre(producto.getNombre());
            productoExiste.setDescripcion(producto.getDescripcion());
            productoExiste.setPrecio(producto.getPrecio());
            productoExiste.setCategoria(producto.getCategoria());
            productoExiste.setImagenUrl(producto.getImagenUrl());
            productoExiste.setNuevo(producto.isNuevo());
            return productoService.guardarProducto(productoExiste);
        }
        return null;
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id){
        productoService.eliminarProducto(id);
    }


}
