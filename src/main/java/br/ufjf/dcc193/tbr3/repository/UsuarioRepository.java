package br.ufjf.dcc193.tbr3.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.dcc193.tbr3.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);
}
