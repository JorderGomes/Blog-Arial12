package br.ufc.quixada.blog.dao.mongo;

import br.ufc.quixada.blog.dao.UserDAO;
import br.ufc.quixada.blog.models.Post;
import br.ufc.quixada.blog.models.Usuario;
import org.apache.catalina.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDaoMongo extends MongoRepository<Usuario, String> {

    public Usuario save(Usuario usuario);
    public Usuario findFirstByEmail(String email);

    public Optional<Usuario> findById(String id);

    public boolean existsById(String id);

    public void deleteById(String id);

    @Query(value = "{ 'email' : ?0 }")
    public Usuario buscaUsuarioPorEmail(String email);
}
