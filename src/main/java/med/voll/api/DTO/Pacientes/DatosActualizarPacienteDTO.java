package med.voll.api.DTO.Pacientes;

import med.voll.api.models.direccion.DatosDireccion;

public record DatosActualizarPacienteDTO(
    int id,
    String nombre,
    String telefono,
    DatosDireccion direccion
) {

}
