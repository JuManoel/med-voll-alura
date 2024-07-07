package med.voll.api.models.pacientes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.models.direccion.DatosDireccion;

public record DatosPaciente(
                @NotBlank @NotNull String nombre,
                @NotBlank @NotNull String email,
                @NotBlank @NotNull String telefono,
                @NotBlank @NotNull @Pattern(regexp = "\\d{5,7}") String documentoIdentidad,
                @NotNull @Valid DatosDireccion direccion) {

}
