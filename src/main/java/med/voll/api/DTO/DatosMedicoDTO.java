package med.voll.api.DTO;

import med.voll.api.models.Especialidad;

public record DatosMedicoDTO(
        int id,
        String nombre,
        Especialidad especialidad,
        String documento,
        String email) {

}
