package med.voll.api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
    public ResponseEntity<DatosMedicoDTO> registrarMedigo(@RequestBody @Valid DatosMedico json,UriComponentsBuilder uriComponentsBuilder) {
        DatosMedicoDTO medicoDTO = service.registrarMedico(json);
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medicoDTO.id()).toUri();
        return ResponseEntity.created(url).body(medicoDTO);
    }

    @GetMapping()
    public ResponseEntity<Page<DatosMedicoDTO>> listarMedicos(@PageableDefault(size = 3, sort = "nombre", page = 0) Pageable page) {
        return ResponseEntity.ok(service.listarMedicos(page));
    }

    @PutMapping()
    @Transactional
    public ResponseEntity<DatosMedicoDTO> actualizarMedico(@RequestBody @Valid DatosActualizarMedicoDTO json) {
        DatosMedicoDTO med = service.actualizarMedico(json);
        return ResponseEntity.ok(med);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosMedicoDTO> eliminarMedico(@PathVariable int id) {
        service.eliminarMedico(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosMedicoDTO> obtenerMedico(@PathVariable int id) {
        DatosMedicoDTO med = service.obtenerMedico(id);
        return ResponseEntity.ok(med);
    }
}
