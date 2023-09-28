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

import java.util.List;
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
    void Dado_producto_Cuando_logica_ver_por_id_Entonces_ver_producto() {
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
    void Dado_producto_nuevo_Cuando_logica_agregar_Entonces_agregar_producto() {
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
    void Dado_producto_existente_Cuando_logica_eliminar_Entonces_borrar_producto_logico() {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(7);
        boolean borrado = logica.eliminarProducto(productoDTO.getId());
        assertTrue(borrado);
        Mockito.verify(repository).deleteById(7);

    }

    @Test
    void Dado_producto_existente_Cuando_logica_actualizar_Entonces_actualizar_producto() {
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
    void Dado_categoria_Cuando_filtrar_por_categoria_correcta_Entonces_obtener_productos_por_categoria() {
        String categoria = "Tecnologia";
        Producto producto = new Producto();

        producto.setId(10);
        producto.setNombre("AR SET");
        producto.setDescripcion("Gafas de realidad aumentada");
        producto.setPrecio(80);
        producto.setStock(6);
        producto.setCategoria(categoria);

        when(repository.findByCategoria(categoria)).thenReturn(List.of(producto));

        List<Producto> resultado = logica.filtrarPorCategoria(categoria);

        assertEquals(1, resultado.size());

        assertEquals(categoria, resultado.get(0).getCategoria());

        Mockito.verify(repository).findByCategoria(categoria);
    }

    @Test
    void obtenerStockPorId() {
        int id = 42;
        int stockEsperado = 10;

        when(repository.obtenerStockPorId(id)).thenReturn(stockEsperado);

        int stockObtenido = logica.obtenerStockPorId(id);

        assertEquals(stockEsperado, stockObtenido);

        Mockito.verify(repository).obtenerStockPorId(id);
    }
}