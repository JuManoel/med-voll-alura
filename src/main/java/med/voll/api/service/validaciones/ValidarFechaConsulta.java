package med.voll.api.service.validaciones;

import java.time.DayOfWeek;

import org.springframework.stereotype.Component;

import med.voll.api.models.consulta.DatosConsulta;

@Component
public class ValidarFechaConsulta implements ValidarConsultas{

    public void validar(DatosConsulta datos){
        var domingo = DayOfWeek.SUNDAY.equals(datos.fecha().getDayOfWeek());
        if(domingo)
            throw new RuntimeException("No se puede realizar una consulta el domingo");
        var horarioAntes = datos.fecha().getHour()<7;
        var horarioDespois = datos.fecha().getHour()>19;
        if(horarioAntes || horarioDespois)
            throw new RuntimeException("No se puede realizar consulta antes de la 7am ni despues de las 7am");
        

    }

}
