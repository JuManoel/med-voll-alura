package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.DTO.DatosPacienteDTO;
import med.voll.api.models.DatosPaciente;
import med.voll.api.service.PacienteService;

@RestController
@RequestMapping(value = "/paciente")
public class PacienteController {
    @Autowired
    private PacienteService service;

    @PostMapping()
    public void registrarPaciente(@RequestBody @Valid DatosPaciente json) {
        service.registrarPaciente(json);
    }

    @GetMapping()
    public Page<DatosPacienteDTO> listarPacientes(@PageableDefault(size = 3, sort = "nombre", page = 0) Pageable page) {
        return service.listarPacientes(page);
    }
}
