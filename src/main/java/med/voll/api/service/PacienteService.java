package med.voll.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import med.voll.api.DTO.DatosActualizarPacienteDTO;
import med.voll.api.DTO.DatosPacienteDTO;
import med.voll.api.models.DatosPaciente;
import med.voll.api.models.Paciente;
import med.voll.api.repository.PacienteRepository;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository repository;

    public void registrarPaciente(DatosPaciente paciente) {
        Paciente pac = new Paciente(paciente);
        repository.save(pac);
    }

    public Page<DatosPacienteDTO> listarPacientes(Pageable pageable) {
        return repository.findByActivoTrue(pageable)
                .map(p -> new DatosPacienteDTO(p.getId(),p.getNombre(), p.getEmail(), p.getTelefono(), p.getDocumento()));
    }

    public void actualizarPaciente(@Valid DatosActualizarPacienteDTO json) {
        Optional<Paciente> paciente = repository.findById(json.id());
        if(!paciente.isPresent()){
            throw new RuntimeException("Paciente no encontrado");
        }
        Paciente pac = paciente.get();
        pac.actualizarDatos(json);
        
    }

    public void eliminarPaciente(int id) {
        Optional<Paciente> paciente = repository.findById(id);
        if(!paciente.isPresent()){
            throw new RuntimeException("Paciente no encontrado");
        }
        paciente.get().desactivar();
    }
}
