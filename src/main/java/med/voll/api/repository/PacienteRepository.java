package med.voll.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.models.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    Page<Paciente> findByActivoTrue(Pageable page);
}
