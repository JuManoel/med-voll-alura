package med.voll.api.DTO;

import med.voll.api.models.DatosDireccion;

public record DatosActualizarPacienteDTO(
    int id,
    String nombre,
    String telefono,
    DatosDireccion direccion
) {

}
