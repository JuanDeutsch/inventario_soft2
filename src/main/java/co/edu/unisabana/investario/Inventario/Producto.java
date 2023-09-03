package co.edu.unisabana.investario.Inventario;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table (name = "inv_rest")
public class Producto {

    @Id
    @Column
    private @Getter @Setter int id;
    @Column
    private @Getter @Setter String nombre;
    @Column
    private @Getter @Setter String descripcion;
    @Column
    private @Getter @Setter int precio;
    @Column
    private @Getter @Setter int cantidadinicial;
    @Column
    private @Getter @Setter String categoria;
    @Column
    private @Getter @Setter Timestamp fechacreacion;
    @Column
    private @Getter @Setter Timestamp fechaact;

}
