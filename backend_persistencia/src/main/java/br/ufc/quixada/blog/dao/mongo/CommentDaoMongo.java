package br.ufc.quixada.blog.dao.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.blog.dao.CommentDAO;
import br.ufc.quixada.blog.models.Comentario;

import org.springframework.context.annotation.Primary;

@Primary
@Repository
public interface CommentDaoMongo extends MongoRepository<Comentario, String> {
    
}
