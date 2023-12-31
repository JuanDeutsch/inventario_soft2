package co.edu.unisabana.inventario.controlador;

import co.edu.unisabana.inventario.bd.Producto;
import co.edu.unisabana.inventario.controlador.dto.ProductoDTO;
import co.edu.unisabana.inventario.controlador.dto.RespuestaDTO;
import co.edu.unisabana.inventario.logica.InventarioLogica;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "localhost:5500/**")
@RestController
@Slf4j
@Api(value = "Inventario", description = "Operaciones relacionadas con la gestión de inventario")
public class InventarioController {

    private final InventarioLogica logica;

    public InventarioController(InventarioLogica logica) {
        this.logica = logica;
    }

    @ApiOperation(value = "Crear un producto", response = Producto.class)
    @PostMapping(path = "/producto/agregar")
    public RespuestaDTO agregarProducto(@RequestBody ProductoDTO productoDTO) {
        try {
            log.info("Agregando producto con ID "+productoDTO.getId()+" en la BD");
            logica.agregarProducto(productoDTO);
            return new RespuestaDTO("Producto guardado correctamente");
        } catch (Exception e) {
            log.info("Producto con ID "+productoDTO.getId()+" no se ha podido guardar");
            return new RespuestaDTO("Se genero un error al guardar el producto");
        }

    }

    @ApiOperation(value = "Eliminar un producto por su ID", response = Producto.class)
    @DeleteMapping(path = "/producto/eliminar")
    public RespuestaDTO borrarProducto(@RequestParam int id) {
        try {
            log.info("Producto con ID "+id+" eliminado");
            logica.eliminarProducto(id);
            return new RespuestaDTO("Producto eliminado correctamente");
        } catch (Exception e) {
            log.info("Eliminación de producto con ID "+id+" fallida");
            return new RespuestaDTO("El producto no se pudo eliminar");
        }
    }

    @ApiOperation(value = "Actualizar un producto", response = Producto.class)
    @PutMapping(path = "/producto/actualizar")
    public RespuestaDTO actualizarProducto(@RequestBody ProductoDTO actProducto) {
        try {
            log.info("Producto con ID "+actProducto.getId()+" actualizado");
            logica.cambiarProducto(actProducto.getId(), actProducto);
            return new RespuestaDTO("El producto ha actualizado");
        } catch (Exception e) {
            log.info("Actualización de producto con ID "+actProducto.getId()+" fallida");
            return new RespuestaDTO("El producto no se pudo actualizar");
        }
    }

    @ApiOperation(value = "Obtener un producto por su ID", response = Producto.class)
    @GetMapping(path = "/producto/verProducto/id")
    public RespuestaDTO verPorID(@RequestParam int id) {
        if (logica.verProductoPorID(id).isEmpty()){
            log.info("Filtro por ID "+id+" fallida");
            return new RespuestaDTO("No existe ese ID");
        }
        else{
            log.info("Filtro por ID "+id+" desplegada");
            return new RespuestaDTO("Este es el Producto: " + logica.verProductoPorID(id));
        }
    }

    @ApiOperation(value = "Obtener productos por categoría", response = Producto.class)
    @GetMapping(path = "/producto/verProductoPorCategoria")
    public List<Producto> filtrarPorCategoria(@RequestParam("categoria") String categoria) {
        List<Producto> filtroCategoria = logica.filtrarPorCategoria(categoria);
        if (filtroCategoria.isEmpty()) {
            log.info("Filtro por categoria "+categoria+" fallida");
            return null;
        } else {
            log.info("Filtro por categoria "+categoria+" desplegada");
            return filtroCategoria;
        }
    }

    @ApiOperation(value = "Obtener un stock por su ID", response = Producto.class)
    @GetMapping(path = "/producto/verStockPorId")
    public int obtenerStockPorId(@RequestParam("id") int id) {
        log.info("Stock de producto con ID "+id+" desplegada");
        return logica.obtenerStockPorId(id);
    }
}

