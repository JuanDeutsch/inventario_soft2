package co.edu.unisabana.inventario.Inventario.logica;

import co.edu.unisabana.inventario.Inventario.bd.Producto;
import co.edu.unisabana.inventario.Inventario.bd.ProductoRepository;
import co.edu.unisabana.inventario.Inventario.controlador.dto.ProductoDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InventarioLogica {

    private ProductoRepository productoRepository;

    public InventarioLogica(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Optional<Producto> verProductoPorID(int id) {
        return productoRepository.findById(id);
    }

    public Producto agregarProducto(ProductoDTO productoDTO) {
        Producto producto = new Producto();
        producto.setId(productoDTO.getId());
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());
        producto.setCategoria(productoDTO.getCategoria());
        producto.setFechaCreacion(LocalDateTime.now());
        producto.setFechaAct(LocalDateTime.now());
        productoRepository.save(producto);
        return producto;
    }

    public boolean eliminarProducto(int id) {
        productoRepository.deleteById(id);
        return true;
    }

    public void cambiarProducto(int id, ProductoDTO actProducto) {
        Producto productoActualizar = productoRepository.getReferenceById(id);
        productoActualizar.setNombre(actProducto.getNombre());
        productoActualizar.setDescripcion(actProducto.getDescripcion());
        productoActualizar.setPrecio(actProducto.getPrecio());
        productoActualizar.setStock(actProducto.getStock());
        productoActualizar.setCategoria(actProducto.getCategoria());
        productoActualizar.setFechaAct(LocalDateTime.now());
        productoRepository.save(productoActualizar);

    }

    public List<Producto> filtrarPorCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    public int obtenerStockPorId(int id) {
        return productoRepository.obtenerStockPorId(id);
    }
}
