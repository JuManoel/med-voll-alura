package med.voll.api.DTO.Medicos;

import jakarta.validation.constraints.NotNull;
import med.voll.api.models.direccion.DatosDireccion;

public record DatosActualizarMedicoDTO(
        @NotNull int id,
        String nombre,
        String documento,
        DatosDireccion direccion) {

}
