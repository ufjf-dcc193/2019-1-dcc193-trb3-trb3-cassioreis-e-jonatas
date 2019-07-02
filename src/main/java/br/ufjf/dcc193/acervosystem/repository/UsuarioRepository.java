package br.ufjf.dcc193.acervosystem.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.dcc193.acervosystem.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);
}
