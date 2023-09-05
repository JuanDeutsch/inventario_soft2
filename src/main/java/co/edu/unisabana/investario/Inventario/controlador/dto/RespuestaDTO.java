package co.edu.unisabana.investario.Inventario.controlador.dto;

import lombok.Data;

@Data
public class RespuestaDTO {
    String mensaje;

    public RespuestaDTO(String mensaje) {
        this.mensaje = mensaje;
    }
}


