package br.ufc.quixada.blog.dao.mongo;

import br.ufc.quixada.blog.dao.PostDAO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.blog.models.Post;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.Optional;

@Repository
// @Primary
public interface PostDaoMongo extends PostDAO, MongoRepository<Post, String> {
    @Query(value = "{ 'usuario._id' : ?0 }")
    public List<Post> findPostsByUsuarioId(String id);

    public boolean existsById(String id);

    public Post save(Post post);

    public void deleteById(String id);

    @Query("{'user.id': ?0}")
    void deleteByUserId(String userId);

    public Optional<Post> findById(String id);

    @Query(value = "{}", count = true)
    public long count();

    @Query(value = "{titulo : {$regex: ?0, $options: 'i'} }")
    public List<Post> findByWordInTitle(String word);

    @Query(value = "{corpo : {$regex: ?0, $options: 'i'} }")
    public List<Post> findByWordInBody(String word);

    @Query(value = "{rate: {$gte: ?0} }")
    public List<Post> findPostsByMinRate(Double min_rate);

    public Integer countPostsByCategoria();
}
