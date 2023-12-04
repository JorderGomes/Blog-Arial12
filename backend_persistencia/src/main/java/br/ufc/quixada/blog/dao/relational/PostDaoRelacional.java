package br.ufc.quixada.blog.dao.relational;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.blog.dao.PostDAO;
import br.ufc.quixada.blog.models.Post;

@Primary
@Repository
public interface PostDaoRelacional extends PostDAO, JpaRepository<Post, String> {

    public Post save(Post post);

    public boolean existsById(String id);

    public void deleteById(String id);

    @Query(value = "delete from posts where usuario_id = :userId", nativeQuery = true)
    void deleteByUserId(String userId);

    @Query("select p from Post p  where p.titulo ilike %:word%")
    public List<Post> findByWordInTitle(String word);

    public Optional<Post> findById(String id);

    @Query("select p from Post p  where p.corpo ilike %:word%")
    public List<Post> findByWordInBody(String word);

    @Query("select p from Post p  where p.rate >= :min_rate")
    public List<Post> findPostsByMinRate(Double min_rate);

    @Query(value = "select count(id) from posts p", nativeQuery = true)
    public long count();

    public Integer countPostsByCategoria(String categoria);

    public List<Post> findPostsByUsuarioId(String id);

}