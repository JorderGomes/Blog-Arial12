package br.ufc.quixada.blog.dao.relational;

import br.ufc.quixada.blog.dao.CommentDAO;
import br.ufc.quixada.blog.models.Comentario;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public interface CommentDaoRelacional extends CommentDAO, JpaRepository<Comentario, String> {

    // @Query("")
    // @Query(value = "select count(id) from posts p", nativeQuery = true)
    @Query(value = "select * from comentarios where post_id = :postId", nativeQuery = true)
    public List<Comentario> findByPostId(String postId);

    public List<Comentario> findByUsuarioId(String userId);

    @Query(value = "delete from comentarios where usuario_id = :userId", nativeQuery = true)
    void deleteByUserId(String userId);

}
