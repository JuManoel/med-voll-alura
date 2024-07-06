package med.voll.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import med.voll.api.DTO.Pacientes.DatosActualizarPacienteDTO;
import med.voll.api.DTO.Pacientes.DatosPacienteDTO;
import med.voll.api.models.pacientes.DatosPaciente;
import med.voll.api.models.pacientes.Paciente;
import med.voll.api.repository.PacienteRepository;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository repository;

    public DatosPacienteDTO registrarPaciente(DatosPaciente paciente) {
        Paciente pac = new Paciente(paciente);
        repository.save(pac);
        DatosPacienteDTO dto = new DatosPacienteDTO(pac.getId(),pac.getNombre(),pac.getEmail(),pac.getTelefono(),pac.getDocumento());
        return dto;
    }

    public Page<DatosPacienteDTO> listarPacientes(Pageable pageable) {
        return repository.findByActivoTrue(pageable)
                .map(p -> new DatosPacienteDTO(p.getId(),p.getNombre(), p.getEmail(), p.getTelefono(), p.getDocumento()));
    }

    public DatosPacienteDTO actualizarPaciente(@Valid DatosActualizarPacienteDTO json) {
        Optional<Paciente> paciente = repository.findById(json.id());
        if(!paciente.isPresent()){
            throw new RuntimeException("Paciente no encontrado");
        }
        Paciente pac = paciente.get();
        pac.actualizarDatos(json);
        DatosPacienteDTO pacienteDTO = new DatosPacienteDTO(pac.getId(), pac.getNombre(), pac.getEmail(), pac.getTelefono(), pac.getDocumento());
        return pacienteDTO;
    }

    public void eliminarPaciente(int id) {
        Optional<Paciente> paciente = repository.findById(id);
        if(!paciente.isPresent()){
            throw new RuntimeException("Paciente no encontrado");
        }
        paciente.get().desactivar();
    }

    public DatosPacienteDTO obtenerPaciente(int id) {
        Optional<Paciente> paciente = repository.findById(id);
        if(!paciente.isPresent()){
            throw new RuntimeException("Paciente no encontrado");
        }
        DatosPacienteDTO pacienteDTO = new DatosPacienteDTO(paciente.get().getId(), paciente.get().getNombre(), paciente.get().getEmail(), paciente.get().getTelefono(), paciente.get().getDocumento());
        return pacienteDTO;
    }
}
