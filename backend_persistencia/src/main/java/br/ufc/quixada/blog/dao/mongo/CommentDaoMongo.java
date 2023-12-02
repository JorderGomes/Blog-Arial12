package br.ufc.quixada.blog.dao.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.blog.dao.CommentDAO;
import br.ufc.quixada.blog.models.Comentario;

@Repository
public interface CommentDaoMongo extends CommentDAO, MongoRepository<Comentario, String> {
    
}
