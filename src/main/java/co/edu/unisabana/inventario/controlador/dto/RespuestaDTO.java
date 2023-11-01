package co.edu.unisabana.inventario.controlador.dto;

import lombok.Data;

@Data
public class RespuestaDTO {
    String mensaje;

    public RespuestaDTO(String mensaje) {
        this.mensaje = mensaje;
    }

    public RespuestaDTO() {
    }
}


