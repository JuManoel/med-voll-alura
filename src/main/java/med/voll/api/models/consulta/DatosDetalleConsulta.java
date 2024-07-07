package med.voll.api.models.consulta;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosDetalleConsulta(
    @NotBlank @NotNull int id_pasciente, 
    @NotBlank @NotNull int id_medico, 
    @NotBlank @NotNull @Future LocalDateTime fecha 
) {

}
