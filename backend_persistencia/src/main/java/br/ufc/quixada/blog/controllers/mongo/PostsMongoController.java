package br.ufc.quixada.blog.controllers.mongo;

// import br.ufc.quixada.blog.dao.PostDAO;
// import br.ufc.quixada.blog.dao.UserDAO;
import br.ufc.quixada.blog.dao.mongo.PostDaoMongo;
import br.ufc.quixada.blog.dao.mongo.UserDaoMongo;
import br.ufc.quixada.blog.models.Post;
import br.ufc.quixada.blog.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// import java.util.Map;
import java.util.Optional;

// import br.ufc.quixada.blog.dao.relational.UserDaoRelacional;


@RestController
@RequestMapping("/posts/mongo")
public class PostsMongoController {
    @Autowired
    UserDaoMongo userDAO;

    @Autowired
    PostDaoMongo postDAO;

    @GetMapping
    public ResponseEntity<List<Post>> getPosts() {
        return ResponseEntity.ok(postDAO.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable String id) {
        Optional<Post> postOpt = postDAO.findById(id);
        return postOpt.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
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

    @GetMapping(value = "/")
    public ResponseEntity<List<Post>> buscarPostsPorIdUsuario(@RequestParam String userId){
        Optional<Usuario> usuario = userDAO.findById(userId);
        if(usuario.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        List<Post> posts = postDAO.findPostsByUserId(userId);
        return ResponseEntity.ok(posts);
    }
    
}
