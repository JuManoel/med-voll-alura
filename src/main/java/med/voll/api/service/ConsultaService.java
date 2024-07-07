package med.voll.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.models.consulta.Consulta;
import med.voll.api.models.consulta.DatosConsulta;
import med.voll.api.models.medicos.Medico;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;


@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository cRepository;
    @Autowired
    private MedicoRepository mRepository;
    @Autowired
    private PacienteRepository pRepository;
    public void generarConsulta(DatosConsulta datosConsulta){
        var paciente = pRepository.findByActivoTrueAndId(datosConsulta.id_pasciente());
        var medico = mRepository.findByActivoTrueAndId(datosConsulta.id_medico());

        if(!paciente.isPresent()){
            throw new RuntimeException("El paciente no está activo o no existe");
        }
        if(!medico.isPresent())
            throw new RuntimeException("El medico no está activo o no existe");

        Consulta consulta = new Consulta(selecionarMedico(datosConsulta),paciente.get(),datosConsulta.fecha());
        cRepository.save(consulta);
    }

    private Medico selecionarMedico(DatosConsulta datos){
        if(datos.id_medico() != null){
            return mRepository.findByActivoTrueAndId(datos.id_medico()).get();
        } 
        if(datos.especialidad() == null){
            throw new RuntimeException("Debe especificar la especialidad");
        }
        return mRepository.selecionarMedicoEspecialistaEnFecha(datos.especialidad(),datos.fecha()).get();
    }

}
