package br.ufc.quixada.blog.dao.mongo;

// import br.ufc.quixada.blog.dao.UserDAO;
import br.ufc.quixada.blog.dao.UserDAO;
import br.ufc.quixada.blog.models.Usuario;
// import org.apache.catalina.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
// import org.springframework.context.annotation.Primary;

@Repository
public interface UserDaoMongo extends UserDAO, MongoRepository<Usuario, String> {

    public Usuario save(Usuario usuario);
    
    public Optional<Usuario> findByEmail(String email);

    public Optional<Usuario> findById(String id);

    public boolean existsById(String id);

    public void delete(Usuario usuario);

    public void deleteById(String id);

    @Query(value = "{ 'email' : :#{#email}} }")
    public Optional<Usuario> findFirstByEmail(String email);

}
