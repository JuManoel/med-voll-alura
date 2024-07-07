package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.models.DatosToken;
import med.voll.api.models.usuarios.DatosUsuario;
import med.voll.api.models.usuarios.Usuario;
import med.voll.api.service.TokenService;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DatosToken> autenticarUsuario(@RequestBody @Valid DatosUsuario datosAutenticacionUsuario) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.login(),
                datosAutenticacionUsuario.password());
        var usuarioAutenticado = manager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosToken(JWTtoken));
    }
}
