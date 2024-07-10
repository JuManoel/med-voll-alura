package med.voll.api.service.validaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.models.consulta.DatosConsulta;
import med.voll.api.repository.PacienteRepository;

@Component
public class PacienteActivo implements ValidarConsultas{
    @Autowired
    private PacienteRepository repository;

    public void validar(DatosConsulta datos) {
        var paciente = repository.findActivoById(datos.id_pasciente());
        if (!paciente) {
            throw new RuntimeException("El paciente no está activo");

        }
    }
}
