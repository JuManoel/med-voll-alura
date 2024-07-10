package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.models.consulta.DatosConsulta;
import med.voll.api.service.ConsultaService;
import med.voll.api.models.consulta.DatosDetalleConsulta;

@RestController
@ResponseBody
@RequestMapping("/consulta")
public class ConsultaController {
    @Autowired
    private ConsultaService service;
    @PostMapping()
    public ResponseEntity<DatosConsulta> agendar(@RequestBody @Valid DatosConsulta consulta){
        var response = service.generarConsulta(consulta);
        return ResponseEntity.ok(response);
    }
}
