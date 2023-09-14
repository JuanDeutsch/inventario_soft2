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
}
