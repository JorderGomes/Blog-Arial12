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
    @Query(value = "select * from comentarios where post_id = :post_id", nativeQuery = true)
    public List<Comentario> findByPostId(int post_id);

}
