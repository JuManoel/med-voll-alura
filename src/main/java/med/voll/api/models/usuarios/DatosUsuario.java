package med.voll.api.models.usuarios;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosUsuario(
    @NotBlank @NotNull String login,
    @NotBlank @NotNull String password
) {

}
