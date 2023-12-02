package br.ufc.quixada.blog.dao.relational;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.blog.dao.PostDAO;
import br.ufc.quixada.blog.models.Comentario;
import br.ufc.quixada.blog.models.Post;
import br.ufc.quixada.blog.models.Usuario;

@Repository
@Primary
public interface PostDaoRelacional extends PostDAO, JpaRepository<Post, String> {
    
    @Query("select p from Post p  where p.titulo ilike %:word%")
    public List<Post> findByWordInTitle(String word);

    @Query("select p from Post p  where p.corpo ilike %:word%")
    public List<Post> findByWordInBody(String word);

    @Query("select p from Post p  where p.rate >= :min_rate")
    public List<Post> findPostsByMinRate(Double min_rate);

    @Query(value = "select count(id) from posts p", nativeQuery = true)
    public int countPosts();

    @Query(value = "select categoria, count(id) from posts p group by p.categoria", nativeQuery = true)
    public List<Map<String, Integer>> countPostsByCategoria();

    public Post findFirstById(String String);

    public Post findFirstByTitulo(String title);

    @Query(name = "buscarAutorPorIdDePost")
    public Usuario buscarAutorPorIdDePost(String id);

    //@Query("SELECT p FROM Post p where Extract(year from to_timestamp(to_char( (p.dataDeCriacao),'YYYY-MM-DD'),'YYYY-MM-DD')) = :ano")

    @Query("SELECT p FROM Post p where Extract(year from p.dataDeCriacao) = :ano")
    public List<Post> buscarPostsPorAnoDeCriacaoPGSQL(int ano);

    @Query("SELECT p FROM Post p where Cast(strftime('%Y', p.dataDeCriacao / 1000, 'unixepoch') as integer) = :ano")
    public List<Post> buscarPostsPorAnoDeCriacaoSQLITE(int ano);

    @Query(name = "buscarComentariosPorIdDoPost")
    public List<Comentario> buscarComentariosPorIdDoPost(String id);

}