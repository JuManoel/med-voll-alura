package med.voll.models;

public record DatosPaciente(String nombre,
        String email,
        String telefono,
        String documentoIdentidad,
        DatosDireccion direccion) {

}
