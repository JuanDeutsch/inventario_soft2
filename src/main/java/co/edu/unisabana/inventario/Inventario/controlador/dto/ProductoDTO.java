package co.edu.unisabana.inventario.Inventario.controlador.dto;

import lombok.Data;

@Data
public class ProductoDTO {
    private int id;
    private String nombre;
    private String descripcion;
    private int precio;
    private int stock;
    private String categoria;

    public ProductoDTO(int id, String nombre, String descripcion, int precio, int stock, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }

    public ProductoDTO() {
    }
}
