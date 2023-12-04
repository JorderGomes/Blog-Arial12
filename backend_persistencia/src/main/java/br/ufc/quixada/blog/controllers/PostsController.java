package br.ufc.quixada.blog.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.quixada.blog.dao.PostDAO;
import br.ufc.quixada.blog.dao.UserDAO;
// import br.ufc.quixada.blog.dao.relational.PostDaoRelacional;
import br.ufc.quixada.blog.models.Post;
import br.ufc.quixada.blog.models.Usuario;

// import br.ufc.quixada.blog.dao.relational.UserDaoRelacional;


@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    UserDAO userDAO;

    @Autowired
    PostDAO postDAO;

    @GetMapping
    public ResponseEntity<List<Post>> getPosts() {
        return ResponseEntity.ok(postDAO.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable String id) {
        Optional<Post> postOpt = postDAO.findById(id);
        return postOpt.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping(value = "/user/{id}")
    public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable String id){
        return ResponseEntity.ok(postDAO.findPostsByUsuarioId(id));
    }

    @GetMapping("/search/title")
    public List<Post> searchByTitle(@RequestParam String term) {
        return postDAO.findByWordInTitle(term);
    }

    @GetMapping("/search/body")
    public List<Post> searchByBody(@RequestParam String term) {
        return postDAO.findByWordInBody(term);
    }

    @GetMapping("/search/rate")
    public List<Post> searchByRate(@RequestParam Double minRate) {
        return postDAO.findPostsByMinRate(minRate);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countPosts() {
        return ResponseEntity.ok(postDAO.count());
    }

    @GetMapping("/countByCategory/{categoria}")
    public ResponseEntity<Integer> countPostsByCategory(@PathVariable String categoria) {
        return ResponseEntity.ok(postDAO.countPostsByCategoria(categoria));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Post> savePost(@RequestBody Post post, @RequestParam String userId) {
        Optional<Usuario> usuarioOpt = userDAO.findById(userId);
        if (usuarioOpt.isPresent()) {
            post.setUsuario(usuarioOpt.get());
            postDAO.save(post);
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable String id) {
        if (postDAO.existsById(id)) {
            postDAO.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value="/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> updatePost(@RequestBody Post updatedPost, @PathVariable String id) {
        Optional<Post> postOpt = postDAO.findById(id);
        if (postOpt.isPresent()) {
            Post existingPost = postOpt.get();
            existingPost.setTitulo(updatedPost.getTitulo());
            existingPost.setCorpo(updatedPost.getCorpo());
            existingPost.setCategoria(updatedPost.getCategoria());
            existingPost.setRate(updatedPost.getRate());
            postDAO.save(existingPost);
            return ResponseEntity.ok(existingPost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
