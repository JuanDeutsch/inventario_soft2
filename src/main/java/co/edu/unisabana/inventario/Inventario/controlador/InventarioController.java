package co.edu.unisabana.inventario.Inventario.controlador;

import co.edu.unisabana.inventario.Inventario.bd.Producto;
import co.edu.unisabana.inventario.Inventario.bd.ProductoRepository;
import co.edu.unisabana.inventario.Inventario.controlador.dto.RespuestaDTO;
import co.edu.unisabana.inventario.Inventario.logica.InventarioLogica;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class InventarioController {

    public InventarioLogica logica;

    public InventarioController(InventarioLogica logica) {
        this.logica = logica;
    }

    @PostMapping(path = "/producto/agregar")
    public RespuestaDTO agregarProducto (@RequestBody Producto producto){
        try{
            logica.agregarProducto(producto);
            return new RespuestaDTO("Producto guardado correctamente");
        }catch(Exception e){
            return new RespuestaDTO("Se genero un error al guardar el producto");
        }

    }

    @DeleteMapping(path = "/producto/eliminar")
    public RespuestaDTO borrarProducto(@RequestParam int id){
        try {
            logica.eliminarProducto(id);
            return new RespuestaDTO("Producto eliminado correctamente");
        }catch (Exception e){
            return new RespuestaDTO("El producto no se pudo eliminar");
        }
    }

    @PutMapping(path = "/producto/actualizar")
    public RespuestaDTO actualizarProducto(@RequestBody Producto actProducto){
        try {
            logica.cambiarProducto(actProducto.getId(), actProducto);
            return new RespuestaDTO("El producto ha actualizado");
        } catch (Exception e){
            return new RespuestaDTO("El producto no se pudo actualizar");
        }
    }

    @GetMapping(path = "/verProducto/id")
    public RespuestaDTO verPorID(@RequestParam int id){
        try {
            logica.verProductoPorID(id);
            return new RespuestaDTO("Este es el Producto: "+logica.verProductoPorID(id));
        } catch (Exception e){
            return new RespuestaDTO("No existe ese ID");
        }
    }

    @GetMapping(path = "/verProductoPorCategoria")
    public List<Producto> verPorCategoria(@Param("categoria") String categoria){
        List<Producto> filtroCategoria = logica.findAll(categoria);
        return filtroCategoria;
    }

}
