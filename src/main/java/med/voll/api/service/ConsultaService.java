package med.voll.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.models.consulta.Consulta;
import med.voll.api.models.consulta.DatosConsulta;
import med.voll.api.models.medicos.Medico;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import med.voll.api.service.validaciones.ValidarConsultas;


@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository cRepository;
    @Autowired
    private MedicoRepository mRepository;
    @Autowired
    private PacienteRepository pRepository;
    
    @Autowired
    private List<ValidarConsultas> validadores;
    public DatosConsulta generarConsulta(DatosConsulta datosConsulta){
        var paciente = pRepository.findByActivoTrueAndId(datosConsulta.id_pasciente());
        var medico = mRepository.findByActivoTrueAndId(datosConsulta.id_medico());

        if(!paciente.isPresent()){
            throw new RuntimeException("El paciente no está activo o no existe");
        }
        
        Consulta consulta = new Consulta(selecionarMedico(datosConsulta),paciente.get(),datosConsulta.fecha());
        validadores.forEach(v->v.validar(datosConsulta));
        cRepository.save(consulta);
        return new DatosConsulta(consulta.getPaciente().getId(), consulta.getMedico().getId(), 
        consulta.getMedico().getEspecialidad(), consulta.getFecha());
    }

    private Medico selecionarMedico(DatosConsulta datos){
        System.out.println(datos);
        var medico = mRepository.findByActivoTrueAndId(datos.id_medico());
        if(medico.isPresent()){
            return medico.get();
        } 
        if(datos.especialidad() == null){
            throw new RuntimeException("Debe especificar la especialidad");
        }
        return mRepository.selecionarMedicoEspecialistaEnFecha(datos.especialidad(),datos.fecha()).get();
    }

}
