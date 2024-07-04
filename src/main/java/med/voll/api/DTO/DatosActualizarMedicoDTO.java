package med.voll.api.DTO;

import jakarta.validation.constraints.NotNull;
import med.voll.api.models.DatosDireccion;

public record DatosActualizarMedicoDTO(
        @NotNull int id,
        String nombre,
        String documento,
        DatosDireccion direccion) {

}
