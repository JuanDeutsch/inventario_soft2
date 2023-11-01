package co.edu.unisabana.inventario.bd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByCategoria(String categoria);

    @Query("SELECT p.stock FROM Producto p WHERE p.id = :id")
    Integer obtenerStockPorId(@Param("id") int id);
}
