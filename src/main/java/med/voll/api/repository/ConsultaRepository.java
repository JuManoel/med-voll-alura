package med.voll.api.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.validation.constraints.NotNull;
import med.voll.api.models.consulta.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta,Integer>{

    Boolean existsByPacienteIdAndFechaBetween(@NotNull int id_pasciente, LocalDateTime primeraHora,
            LocalDateTime ultimaHora);

    Boolean existsByMedicoIdAndFechaBetween(@NotNull int id_pasciente, LocalDateTime primeraHora,
            LocalDateTime ultimaHora);

}
