package med.voll.api.service.validaciones;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.models.consulta.DatosConsulta;

@Component
public class ValidarHorarioConsulta implements ValidarConsultas{

    public void validar(DatosConsulta datos){
        var esAntecipada = LocalDateTime.now();
        var hora = datos.fecha();
        var valida = Duration.between(esAntecipada, hora).toMinutes()<30;
        if(valida)
            throw new IllegalStateException("Tiene que reservar com pelo menos 30 minutos de antecipacion");
    }

}
