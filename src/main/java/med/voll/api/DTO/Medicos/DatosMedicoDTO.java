package med.voll.api.DTO.Medicos;

import med.voll.api.models.medicos.Especialidad;

public record DatosMedicoDTO(
        int id,
        String nombre,
        Especialidad especialidad,
        String documento,
        String email) {

}
