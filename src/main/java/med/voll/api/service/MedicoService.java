package med.voll.api.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import med.voll.api.DTO.DatosActualizarMedicoDTO;
import med.voll.api.DTO.DatosMedicoDTO;
import med.voll.api.models.DatosMedico;
import med.voll.api.models.Medico;
import med.voll.api.repository.MedicoRepository;

@Service
public class MedicoService {
    @org.springframework.beans.factory.annotation.Autowired(required = true)
    private MedicoRepository repository;

    public void registrarMedico(DatosMedico medico) {
        Medico med = new Medico(medico);
        repository.save(med);
    }

    public Page<DatosMedicoDTO> listarMedicos(Pageable pageable) {
        return repository.findAll(pageable)
                .map(m -> new DatosMedicoDTO(m.getId(),m.getNombre(), m.getEspecialidad(), m.getDocumento(), m.getEmail()));
    }

    public void actualizarMedico(@Valid DatosActualizarMedicoDTO json) {
        Optional<Medico> medico = repository.findById(json.id());
        if(!medico.isPresent()){
            throw new RuntimeException("Medico no encontrado");
        }
        Medico med = medico.get();
        med.actualizarDatos(json);
        
    }
}
