package br.ufc.quixada.blog.dao.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.ufc.quixada.blog.dao.PostDAO;
import br.ufc.quixada.blog.models.Post;

public interface PostDaoMongo extends PostDAO, MongoRepository<Post, String> {
    
}
