package med.voll.api.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import med.voll.api.models.pacientes.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    Page<Paciente> findByActivoTrue(Pageable page);

    Optional<Paciente> findByActivoTrueAndId(int id);
    @Query("""
            SELECT p.activo FROM Paciente p WHERE p.id = :id
            """)
    Boolean findActivoById(int id);

}
