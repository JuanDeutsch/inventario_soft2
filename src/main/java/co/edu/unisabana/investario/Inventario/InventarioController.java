package co.edu.unisabana.investario.Inventario;

import co.edu.unisabana.investario.Inventario.logica.InventarioLogica;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventarioController {

    //private InventarioLogica logica;

    /*public InventarioController(InventarioLogica logica) {
        this.logica = logica;
    }*/

    private ProductoRepository productoRepository;

    public InventarioController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @GetMapping(path = "/test")
    public String testeo(){
        return "Holiwis";
    }

    @GetMapping(path = "/verProducto")
    public List<Producto> buscarProductos(){
        return productoRepository.findAll();
    }

    @PostMapping(path = "/producto/agregar")
    public boolean agregarProducto (@RequestBody Producto producto){
        productoRepository.save(producto);
        return true;
    }

    @DeleteMapping(path = "/producto/eliminar")
    public boolean eliminarProducto (){
        productoRepository.deleteAll();
        return true;
    }

}
