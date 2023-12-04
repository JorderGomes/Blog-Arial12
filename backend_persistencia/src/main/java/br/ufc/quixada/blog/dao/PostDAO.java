package br.ufc.quixada.blog.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import br.ufc.quixada.blog.models.Post;
// import br.ufc.quixada.blog.models.Post;

import org.springframework.stereotype.Component;

@Component
public interface PostDAO{

    // Crud

    public Post save(Post post);

    public List<Post> findAll();

    public Optional<Post> findById(String id);

    public void deleteById(String id);
    
    public boolean existsById(String id);

    public void deleteByUserId(String id);


    // Operações específicas

    public long count();

    public List<Post> findByWordInTitle(String word);

    public List<Post> findByWordInBody(String word);

    public List<Post> findPostsByMinRate(Double min_rate);

    public Integer countPostsByCategoria(String categoria);

    public List<Post> findPostsByUsuarioId(String id);


}
