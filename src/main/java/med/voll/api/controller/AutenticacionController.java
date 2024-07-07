package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.models.DatosTolken;
import med.voll.api.models.usuarios.DatosUsuario;
import med.voll.api.models.usuarios.Usuario;
import med.voll.api.service.AutenticacionService;
import med.voll.api.service.TokienService;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private AutenticacionService service;

    @Autowired
    private TokienService tokienService;

    @PostMapping()
    public ResponseEntity<DatosTolken> autenticarUsuario(@RequestBody @Valid DatosUsuario usuario){
        // Implementar la lógica de autenticación
        // Retornar un token de acceso si el usuario es válido
        Authentication authentication = new UsernamePasswordAuthenticationToken(usuario.login(), usuario.password());
        var usuarioAutenticado = manager.authenticate(authentication);
        var tolkin = tokienService.generarTokien((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosTolken(tolkin));
    }
}
