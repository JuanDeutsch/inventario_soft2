package co.edu.unisabana.inventario.Inventario.integracion;

import co.edu.unisabana.inventario.Inventario.bd.Producto;
import co.edu.unisabana.inventario.Inventario.controlador.dto.ProductoDTO;
import co.edu.unisabana.inventario.Inventario.controlador.dto.RespuestaDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
class InventarioControllerTest {

    @Autowired
    TestRestTemplate rest;

    @Test
    void Dado_no_dto_existente_Cuando_controlador_agregar_producto_Entonces_agrega_dto() {
        ProductoDTO dtoAgr = new ProductoDTO(8, "Guitarra", "Intrumento de cuerdas", 13, 15, "Instrumentos");
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
    void Dado_dto_existente_Cuando_controlador_borrar_producto_Entonces_no_borra_dto() {
        ProductoDTO dtoDel = new ProductoDTO(7, "Guitarra", "Instrumento de cuerdas", 13, 15, "Instrumentos");

        ResponseEntity<RespuestaDTO> respuestaBorrar = rest.exchange(
                "/producto/eliminar?id=" + dtoDel.getId(), HttpMethod.DELETE, null, RespuestaDTO.class);
        assertEquals("El producto no se pudo eliminar", respuestaBorrar.getBody().getMensaje());
    }

    @Test
    void Dado_dto_existente_Cuando_controlador_actualizar_producto_Entonces_actualiza_dto() {
        ProductoDTO dtoAct = new ProductoDTO(7, "Guitarra", "Instrumento de cuerdas", 20, 25, "Instrumentos");
        ResponseEntity<RespuestaDTO> respuestaAgregar = rest.postForEntity(
                "/producto/agregar", dtoAct, RespuestaDTO.class);
        assertEquals("Producto guardado correctamente", respuestaAgregar.getBody().getMensaje());

        ResponseEntity<RespuestaDTO> respuestaActualizar = rest.exchange(
                "/producto/actualizar?id=" + dtoAct.getId(), HttpMethod.PUT, new HttpEntity<>(dtoAct), RespuestaDTO.class);

        RespuestaDTO respuesta = respuestaActualizar.getBody();
        assertEquals("El producto ha actualizado", respuesta.getMensaje());
    }

    @Test
    void Dado_dto_existente_Cuando_controlador_actualizar_producto_Entonces_no_actualiza_dto() {
        ProductoDTO dtoAct = new ProductoDTO(9, "Guitarra", "Instrumento de cuerdas", 20, 25, "Instrumentos");

        ResponseEntity<RespuestaDTO> respuestaActualizar = rest.exchange(
                "/producto/actualizar?id=" + dtoAct.getId(), HttpMethod.PUT, new HttpEntity<>(dtoAct), RespuestaDTO.class);

        RespuestaDTO respuesta = respuestaActualizar.getBody();
        assertEquals("El producto no se pudo actualizar", respuesta.getMensaje());
    }

    @Test
    void Dado_dto_existente_Cuando_controlador_ver_producto_por_id_Entonces_visualiza_dto_nombre() {
        ProductoDTO dtoVer = new ProductoDTO(7, "Guitarra", "Instrumento de cuerdas", 20, 25, "Instrumentos");
        ResponseEntity<RespuestaDTO> respuestaAgregar = rest.postForEntity(
                "/producto/agregar", dtoVer, RespuestaDTO.class);
        assertEquals("Producto guardado correctamente", respuestaAgregar.getBody().getMensaje());

        ResponseEntity<RespuestaDTO> respuestaVer = rest.exchange(
                "/verProducto/id?id=" + dtoVer.getId(), HttpMethod.GET, new HttpEntity<>(dtoVer), RespuestaDTO.class);

        RespuestaDTO respuesta = respuestaVer.getBody();
        assertTrue(respuesta.getMensaje().contains("Guitarra"));
    }

    @Test
    void Dado_categoria_Cuando_controlador_busqueda_por_categoria_Entonces_obtener_productos_por_categoria() {
        ProductoDTO dtoCat = new ProductoDTO(8, "Guitarra", "Intrumento de cuerdas", 13, 15, "Instrumentos");
        ResponseEntity<RespuestaDTO> respuesta = rest.postForEntity(
                "/producto/agregar", dtoCat, RespuestaDTO.class);
        assertEquals("Producto guardado correctamente", respuesta.getBody().getMensaje());

        ResponseEntity<List<Producto>> response = rest.exchange(
                "/verProductoPorCategoria?categoria=" + dtoCat.getCategoria(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });

        List<Producto> productoCat = response.getBody();

        boolean categoriaCorrectaEnProductos = productoCat.stream()
                .anyMatch(producto -> "Instrumentos".equals(dtoCat.getCategoria()));
        assertTrue(categoriaCorrectaEnProductos);
    }

    @Test
    void Dado_id_producto_Cuando_controlador_obtener_stock_por_id_Entonces_obtener_stock_por_id() {
        ProductoDTO dtoSto = new ProductoDTO(8, "Guitarra", "Intrumento de cuerdas", 13, 15, "Instrumentos");
        ResponseEntity<RespuestaDTO> respuesta = rest.postForEntity(
                "/producto/agregar", dtoSto, RespuestaDTO.class);
        assertEquals("Producto guardado correctamente", respuesta.getBody().getMensaje());

        ResponseEntity<Integer> response = rest.exchange(
                "/verStockPorId?id=" + dtoSto.getId(),
                HttpMethod.GET,
                null,
                Integer.class);

        Integer stock = response.getBody();
        assertNotNull(stock);
        assertTrue(stock >= 0);
    }
}