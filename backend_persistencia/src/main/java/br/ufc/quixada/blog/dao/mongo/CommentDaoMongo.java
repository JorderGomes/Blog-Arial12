package br.ufc.quixada.blog.dao.mongo;

import br.ufc.quixada.blog.dao.CommentDAO;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.blog.models.Comentario;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentDaoMongo extends CommentDAO, MongoRepository<Comentario, String> {

    public Comentario save(Comentario comentario);

    public Optional<Comentario> findById(String id);

    public void deleteById(String id);

    @Query(value = "{'post._id': ?0}")
    public List<Comentario> findByPostId(String postId);

    @Query(value = "{'usuario._id': ?0}")
    public List<Comentario> findByUsuarioId(String userId);

    @Query("{'user.id': ?0}")
    void deleteByUserId(String userId);



}
