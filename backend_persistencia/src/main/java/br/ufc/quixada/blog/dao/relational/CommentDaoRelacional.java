package br.ufc.quixada.blog.dao.relational;

import br.ufc.quixada.blog.dao.CommentDAO;
import br.ufc.quixada.blog.models.Comentario;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Primary
// @Repository
public interface CommentDaoRelacional extends CommentDAO, JpaRepository<Comentario, String> {

    // @Query("")
    // @Query(value = "select count(id) from posts p", nativeQuery = true)
    @Query(value = "select * from comentarios where post_id = :post_id", nativeQuery = true)
    public List<Comentario> findByPostId(String post_id);

}
