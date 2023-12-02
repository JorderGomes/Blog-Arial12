package br.ufc.quixada.blog.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import br.ufc.quixada.blog.models.Post;
// import br.ufc.quixada.blog.models.Post;


public interface PostDAO{

    // Crud

    public void save(Post post);

    public List<Post> findAll();

    public Optional<Post> findById(String id);

    public void deleteById(String id);
    
    public boolean existsById(String id);

    // Operações específicas

    public int countPosts();

    public Post findFirstByTitulo(String title);

    public List<Post> findByWordInTitle(String word);

    public List<Post> findByWordInBody(String word);

    public List<Post> findPostsByMinRate(Double min_rate);

    public List<Map<String, Integer>> countPostsByCategoria();


}
