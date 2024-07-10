package med.voll.api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.DTO.Pacientes.DatosActualizarPacienteDTO;
import med.voll.api.DTO.Pacientes.DatosPacienteDTO;
import med.voll.api.models.pacientes.DatosPaciente;
import med.voll.api.service.PacienteService;

@RestController
@RequestMapping(value = "/paciente")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {
    @Autowired
    private PacienteService service;

    @PostMapping()
    public ResponseEntity<DatosPacienteDTO> registrarPaciente(@RequestBody @Valid DatosPaciente json,
    UriComponentsBuilder uriComponentsBuilder) {
        DatosPacienteDTO paciente = service.registrarPaciente(json);
        URI url = uriComponentsBuilder.path("/paciente").buildAndExpand(paciente.id()).toUri();
        return ResponseEntity.created(url).body(paciente);

    }

    @GetMapping()
    public ResponseEntity<Page<DatosPacienteDTO>> listarPacientes(@PageableDefault(size = 3, sort = "nombre", page = 0) Pageable page) {
        return ResponseEntity.ok(service.listarPacientes(page));
    }


    @PutMapping()
    @Transactional
    public ResponseEntity<DatosPacienteDTO> actualizarPaciente(@RequestBody @Valid DatosActualizarPacienteDTO json) {
        DatosPacienteDTO paciente = service.actualizarPaciente(json);
        return ResponseEntity.ok(paciente);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosPacienteDTO> eliminarPaciente(@PathVariable int id) {
        service.eliminarPaciente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosPacienteDTO> obtenerPaciente(@PathVariable int id) {
        return ResponseEntity.ok(service.obtenerPaciente(id));
    }
}
