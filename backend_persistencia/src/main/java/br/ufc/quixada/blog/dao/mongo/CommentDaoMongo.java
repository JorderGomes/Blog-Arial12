package br.ufc.quixada.blog.dao.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.blog.models.Comentario;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentDaoMongo extends MongoRepository<Comentario, String> {

    @Query(value = "{_id:  :#{#id}}")
    public Optional<Comentario> findComentarioById(String id);

    @Query(value = "{'post._id': ?0}")
    public List<Comentario> findByPostId(String postId);

    @Query(value = "{'usuario._id': ?0}")
    public List<Comentario> findByUserId(String userId);



}
