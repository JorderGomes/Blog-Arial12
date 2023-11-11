package br.ufc.quixada.blog.dao;

import br.ufc.quixada.blog.models.Comentario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDAO extends JpaRepository<Comentario, Integer> {

    // @Query("")
    // @Query(value = "select count(id) from posts p", nativeQuery = true)
    @Query(value = "select c from comentarios c  where c.post_id = :id_post", nativeQuery = true)
    public List<Comentario> findByPost(int id_post);

}
