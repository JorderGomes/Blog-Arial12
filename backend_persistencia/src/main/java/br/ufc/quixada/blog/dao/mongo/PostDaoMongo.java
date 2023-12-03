package br.ufc.quixada.blog.dao.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.blog.dao.PostDAO;
import br.ufc.quixada.blog.models.Post;

@Repository
public interface PostDaoMongo extends MongoRepository<Post, String> {
    
}
