package co.edu.unisabana.investario.Inventario.logica;

import co.edu.unisabana.investario.Inventario.ProductoRepository;

public class InventarioLogica {

    private ProductoRepository productoRepository;

    public InventarioLogica(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }
}
