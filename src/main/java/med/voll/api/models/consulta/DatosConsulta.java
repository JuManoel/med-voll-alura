package med.voll.api.models.consulta;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.models.medicos.Especialidad;

public record DatosConsulta(
    @NotNull int id_pasciente, 
    @NotNull int id_medico,
    Especialidad especialidad, 
    @NotNull @Future LocalDateTime fecha
) {

}
