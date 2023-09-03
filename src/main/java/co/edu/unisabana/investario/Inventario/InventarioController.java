package co.edu.unisabana.investario.Inventario;

import co.edu.unisabana.investario.Inventario.logica.InventarioLogica;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
