package br.ufc.quixada.blog.dao.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

// import br.ufc.quixada.blog.dao.PostDAO;
import br.ufc.quixada.blog.models.Post;
import org.springframework.context.annotation.Primary;
// import org.springframework.context.annotation.Primary;

import java.util.List;

@Repository
@Primary
public interface PostDaoMongo extends MongoRepository<Post, String> {
    @Query(value = "{ 'usuario._id' : ?0 }")
    public List<Post> findPostsByUserId(String id);

    @Query("{'titulo' : { $regex: ?0, $options: 'i' } }")
    public List<Post> findByTituloRegexIgnoreCase(String substring);

    @Query("{'corpo' : { $regex: ?0, $options: 'i' } }")
    public List<Post> findByCorpoRegexIgnoreCase(String substring);

    @Query(value = "{}", count = true)
    public Long countAllPosts();

    @Query("{ 'rate' : { $gte: ?0 } }")
    List<Post> findPostsByMinRate(double minRate);
}
