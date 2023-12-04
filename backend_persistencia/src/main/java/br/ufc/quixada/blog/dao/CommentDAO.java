package br.ufc.quixada.blog.dao;

import java.util.List;
import java.util.Optional;

import br.ufc.quixada.blog.models.Comentario;
import org.springframework.stereotype.Component;

@Component
public interface CommentDAO {

    // Crud

    public Comentario save(Comentario comentario);

    public List<Comentario> findAll();

    public Optional<Comentario> findById(String id);

    public void deleteById(String id);

    public boolean existsById(String id);

    public void deleteByUserId(String id);

    // Operações específicas

    public List<Comentario> findByPostId(String post_id);

    public List<Comentario> findByUsuarioId(String userId);

}
