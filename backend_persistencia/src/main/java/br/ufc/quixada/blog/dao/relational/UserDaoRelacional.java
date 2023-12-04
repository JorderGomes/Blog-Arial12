package br.ufc.quixada.blog.dao.relational;

import br.ufc.quixada.blog.dao.UserDAO;
// import br.ufc.quixada.blog.models.Post;
import br.ufc.quixada.blog.models.Usuario;

// import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public interface UserDaoRelacional extends UserDAO, JpaRepository<Usuario, String> {

    public void deleteById(String id);

    public void delete(Usuario usuario);

    public Optional<Usuario> findFirstByEmail(String email);

}
