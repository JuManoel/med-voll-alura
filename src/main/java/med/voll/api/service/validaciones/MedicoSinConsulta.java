package med.voll.api.service.validaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.models.consulta.DatosConsulta;
import med.voll.api.repository.ConsultaRepository;

@Component
public class MedicoSinConsulta implements ValidarConsultas{
@Autowired
    private ConsultaRepository repository;
    public void validar(DatosConsulta datos){
            var primeraHora = datos.fecha().withHour(7);
            var ultimaHora = datos.fecha().withHour(18);

            var medicoConsulta = repository.existsByMedicoIdAndFechaBetween(datos.id_pasciente(),primeraHora,ultimaHora);
            if(medicoConsulta){
                throw new RuntimeException("El Medico ya tiene una consulta en ese horario");
            }
    }
}
