package co.edu.unisabana.inventario.Inventario.integracion;

import co.edu.unisabana.inventario.Inventario.bd.ProductoRepository;
import co.edu.unisabana.inventario.Inventario.controlador.dto.ProductoDTO;
import co.edu.unisabana.inventario.Inventario.controlador.dto.RespuestaDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
class InventarioControllerTest {

    @Autowired
    TestRestTemplate rest;

    @Mock
    ProductoRepository repository;

    @Test
    void agregarProductoTest() {
        ProductoDTO dto = new ProductoDTO(7, "Guitarra", "Intrumento de cuerdas", 13, 15, "Instrumentos");
        ResponseEntity<RespuestaDTO> respuesta = rest.postForEntity(
                "/producto/agregar", dto, RespuestaDTO.class);
        assertEquals("Producto guardado correctamente", respuesta.getBody().getMensaje());
    }

    @Test
    void borrarProducto() {
    }


    @Test
    void actualizarProducto() {
    }

    @Test
    void verPorID() {
        ProductoDTO dto = new ProductoDTO(7, "Guitarra", "Intrumento de cuerdas", 13, 15, "Instrumentos");
    }

    @Test
    void filtrarPorCategoria() {
    }

    @Test
    void obtenerStockPorId() {
    }
}