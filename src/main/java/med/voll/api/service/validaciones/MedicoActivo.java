package med.voll.api.service.validaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.models.consulta.DatosConsulta;
import med.voll.api.repository.MedicoRepository;

@Component
public class MedicoActivo implements ValidarConsultas{
@Autowired
    private MedicoRepository repository;

    public void validar(DatosConsulta datos) {
        var medico = repository.findActivoById(datos.id_pasciente());
        if (!medico) {
            throw new RuntimeException("El paciente no está activo");

        }
    }
}
