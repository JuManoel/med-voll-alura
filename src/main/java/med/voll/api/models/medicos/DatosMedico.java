package med.voll.api.models.medicos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.models.DatosDireccion;

public record DatosMedico(
        @NotNull(message = "todo medico tiene un nombre") @NotBlank(message = "Falta escribir el nombre del medico") String nombre,
        @NotNull @NotBlank @Pattern(regexp = "\\d{4,6}") String documento,
        @NotNull @Email String email,
        @NotNull String telefono,
        @NotNull Especialidad especialidad,
        @NotNull @Valid DatosDireccion dirrecion) {

}
