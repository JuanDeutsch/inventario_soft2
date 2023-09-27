package co.edu.unisabana.inventario.Inventario.unitario;

import co.edu.unisabana.inventario.Inventario.bd.Producto;
import co.edu.unisabana.inventario.Inventario.bd.ProductoRepository;
import co.edu.unisabana.inventario.Inventario.controlador.dto.ProductoDTO;
import co.edu.unisabana.inventario.Inventario.logica.InventarioLogica;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InventarioLogicaTest {

    @InjectMocks
    InventarioLogica logica;

    @Mock
    ProductoRepository repository;

    @Test
    void verProductoPorID() {
        Producto producto = new Producto();
        producto.setId(7);
        producto.setNombre("Guitarra");
        producto.setDescripcion("Instrumento de cuerdas comun");
        producto.setPrecio(13);
        producto.setStock(15);
        producto.setCategoria("Instrumentos");

        when(repository.findById(producto.getId())).thenReturn(Optional.of(producto));

        Optional<Producto> productoOpt = logica.verProductoPorID(7);

        assertTrue(productoOpt.isPresent());
        assertEquals(producto, productoOpt.get());

        Mockito.verify(repository).findById(producto.getId());
    }

    @Test
    void agregarProductoTest() {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(7);
        productoDTO.setNombre("Guitarra");
        productoDTO.setDescripcion("Instrumento de cuerdas comun");
        productoDTO.setPrecio(13);
        productoDTO.setStock(15);
        productoDTO.setCategoria("Instrumentos");
        Producto producto = logica.agregarProducto(productoDTO);
        assertEquals(7, producto.getId());
        assertEquals("Guitarra", producto.getNombre());
        assertEquals("Instrumento de cuerdas comun", producto.getDescripcion());
        assertEquals(13, producto.getPrecio());
        assertEquals(15, producto.getStock());
        assertEquals("Instrumentos", producto.getCategoria());
        Mockito.verify(repository).save(producto);

    }

    @Test
    void eliminarProductoTest() {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(7);
        boolean borrado = logica.eliminarProducto(productoDTO.getId());
        assertTrue(borrado);
        Mockito.verify(repository).deleteById(7);

    }

    @Test
    void cambiarProducto() {
        int id = 7;
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(id);
        productoDTO.setNombre("Guitarra");
        productoDTO.setDescripcion("Instrumento de cuerdas común");
        productoDTO.setPrecio(13);
        productoDTO.setStock(15);
        productoDTO.setCategoria("Instrumentos");

        Producto producto = new Producto();

        when(repository.getReferenceById(id)).thenReturn(producto);
        logica.cambiarProducto(id, productoDTO);

        assertEquals(productoDTO.getNombre(), producto.getNombre());
        assertEquals(productoDTO.getDescripcion(), producto.getDescripcion());
        assertEquals(productoDTO.getPrecio(), producto.getPrecio());
        assertEquals(productoDTO.getStock(), producto.getStock());
        assertEquals(productoDTO.getCategoria(), producto.getCategoria());

        Mockito.verify(repository).save(producto);
    }

    @Test
    void filtrarPorCategoria() {
    }

    @Test
    void obtenerStockPorId() {
    }
}