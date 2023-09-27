package co.edu.unisabana.inventario.Inventario.integracion;

import co.edu.unisabana.inventario.Inventario.controlador.dto.ProductoDTO;
import co.edu.unisabana.inventario.Inventario.controlador.dto.RespuestaDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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

    @Test
    void agregarProductoTest() {
        ProductoDTO dtoAgr = new ProductoDTO(7, "Guitarra", "Intrumento de cuerdas", 13, 15, "Instrumentos");
        ResponseEntity<RespuestaDTO> respuesta = rest.postForEntity(
                "/producto/agregar", dtoAgr, RespuestaDTO.class);
        assertEquals("Producto guardado correctamente", respuesta.getBody().getMensaje());
    }

    @Test
    void Dado_dto_existente_Cuando_controlador_borrar_producto_Entonces_borra_dto() {
        ProductoDTO dtoDel = new ProductoDTO(7, "Guitarra", "Instrumento de cuerdas", 13, 15, "Instrumentos");
        ResponseEntity<RespuestaDTO> respuestaAgregar = rest.postForEntity(
                "/producto/agregar", dtoDel, RespuestaDTO.class);
        assertEquals("Producto guardado correctamente", respuestaAgregar.getBody().getMensaje());

        ResponseEntity<RespuestaDTO> respuestaBorrar = rest.exchange(
                "/producto/eliminar?id=" + dtoDel.getId(), HttpMethod.DELETE, null, RespuestaDTO.class);
        assertEquals("Producto eliminado correctamente", respuestaBorrar.getBody().getMensaje());
    }

    @Test
    void Dado_dto_existente_Cuando_controlador_actualizar_producto_Entonces_actualiza_dto() {
        ProductoDTO dtoAct = new ProductoDTO(7, "Guitarra", "Instrumento de cuerdas", 20, 25, "Instrumentos");
        ResponseEntity<RespuestaDTO> respuestaAgregar = rest.postForEntity(
                "/producto/agregar", dtoAct, RespuestaDTO.class);
        assertEquals("Producto guardado correctamente", respuestaAgregar.getBody().getMensaje());

        ResponseEntity<RespuestaDTO> respuestaActualizar = rest.exchange(
                "/producto/actualizar?id=", HttpMethod.PUT, new HttpEntity<>(dtoAct), RespuestaDTO.class);

        RespuestaDTO respuesta = respuestaActualizar.getBody();
        assertEquals("El producto ha actualizado", respuesta.getMensaje());
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