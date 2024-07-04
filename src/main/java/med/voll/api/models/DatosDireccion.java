package med.voll.api.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosDireccion(
                @NotNull String calle,
                @NotBlank String distrito,
                @NotBlank String ciudad,
                @NotNull int numero,
                @NotBlank String complemento) {

}
