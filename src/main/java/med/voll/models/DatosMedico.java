package med.voll.models;

public record DatosMedico(
        String nombre,
        String documento,
        String email,
        Especialidad especialidad,
        DatosDireccion dirrecion) {

}
