package med.voll.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.models.usuarios.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
