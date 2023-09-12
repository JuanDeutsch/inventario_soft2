package co.edu.unisabana.inventario.Inventario.bd;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "inv_rest")
@Data
public class Producto {

    @Id
    @Column
    private int id;
    @Column
    private String nombre;
    @Column
    private String descripcion;
    @Column
    private int precio;
    @Column
    private int stock;
    @Column
    private String categoria;
    @Column(name = "fechacreacion")
    @CreatedDate
    private LocalDateTime fechaCreacion;
    @Column(name = "fechaact")
    @LastModifiedDate
    private LocalDateTime fechaAct;

}
