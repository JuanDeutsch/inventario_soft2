package co.edu.unisabana.inventario.Inventario.integracion;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
class InventarioControllerTest {

    @Test
    void agregarProductoTest() {
    }

    @Test
    void borrarProducto() {
    }

    @Test
    void actualizarProducto() {
    }

    @Test
    void verPorID() {
    }

    @Test
    void filtrarPorCategoria() {
    }

    @Test
    void obtenerStockPorId() {
    }
}