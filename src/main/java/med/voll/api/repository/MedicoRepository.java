package med.voll.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.models.medicos.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {
    Page<Medico> findByActivoTrue(Pageable page);
}
