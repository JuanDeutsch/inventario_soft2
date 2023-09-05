package co.edu.unisabana.investario.Inventario;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    @CreatedDate
    private @Getter @Setter LocalDate fechacreacion;
    @Column
    @LastModifiedDate
    private @Getter @Setter LocalDate fechaact;

}
