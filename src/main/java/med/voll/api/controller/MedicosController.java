package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.DTO.DatosActualizarMedicoDTO;
import med.voll.api.DTO.DatosMedicoDTO;
import med.voll.api.models.DatosMedico;
import med.voll.api.service.MedicoService;

@RestController
@RequestMapping("/medicos")
public class MedicosController {
    @Autowired
    private MedicoService service;

    @PostMapping()
    public void registrarMedigo(@RequestBody @Valid DatosMedico json) {
        service.registrarMedico(json);
    }

    @GetMapping()
    public Page<DatosMedicoDTO> listarMedicos(@PageableDefault(size = 3, sort = "nombre", page = 0) Pageable page) {
        return service.listarMedicos(page);
    }

    @PutMapping()
    @Transactional
    public void actualizarMedico(@RequestBody @Valid DatosActualizarMedicoDTO json) {
        service.actualizarMedico(json);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarMedico(@PathVariable int id) {
        service.eliminarMedico(id);
    }
}
