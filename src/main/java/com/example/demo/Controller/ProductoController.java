package com.example.demo.Controller;

import com.example.demo.Entities.Producto;
import com.example.demo.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;


    @GetMapping
    public List<Producto> GetAll(){
        return productoRepository.findAll();
    }
    @GetMapping("/{id}")
    public Producto findById(@PathVariable Long id){
        return productoRepository.findById(id).
                orElseThrow(()-> new RuntimeException("Producto no encontrado"));
    }

    @PostMapping
    public Producto CrearProducto(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }
    @PutMapping
    public Producto ActualizarProducto(@PathVariable Long id, @RequestBody Producto DetalleProducto) {
        Producto producto = productoRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            producto.setNombre(DetalleProducto.getNombre());
            producto.setPrecio(DetalleProducto.getPrecio());
            return productoRepository.save(producto);
    }
    @DeleteMapping("/{id}")
    public String EliminarProducto(@PathVariable Long id){
        Producto producto = productoRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        productoRepository.delete(producto);
        return "Producto con el  id: " + id + "fue  eliminado";
    }
}
