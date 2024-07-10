package med.voll.api.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.models.medicos.Especialidad;
import med.voll.api.models.medicos.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {
    Page<Medico> findByActivoTrue(Pageable page);
    Optional<Medico> findByActivoTrueAndId(int id);

    @Query("""
            SELECT m FROM Medico m
            WHERE m.activo= TRUE 
            AND
            m.especialidad=:especialidad 
            AND
            m.id NOT IN(  
                SELECT c.medico.id FROM Consulta c
                WHERE
                c.fecha=:fecha
            )
            ORDER BY RANDOM()
            LIMIT 1
            """)
    Optional<Medico> selecionarMedicoEspecialistaEnFecha(Especialidad especialidad,
            @NotNull @Future LocalDateTime fecha);

    @Query("""
            SELECT m.activo FROM Medico m WHERE m.id = :id
            """)
    Boolean findActivoById(int id);

}
