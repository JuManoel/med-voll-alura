package med.voll.api.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosMedico(
        @NotNull @NotBlank String nombre,
        @NotNull @NotBlank @Pattern(regexp = "\\d{4,6}") String documento,
        @NotNull @Email String email,
        @NotNull String telefono,
        @NotNull Especialidad especialidad,
        @NotNull @Valid DatosDireccion dirrecion) {

}
