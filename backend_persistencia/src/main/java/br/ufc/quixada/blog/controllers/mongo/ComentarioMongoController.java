package br.ufc.quixada.blog.controllers.mongo;

// import br.ufc.quixada.blog.dao.CommentDAO;
// import br.ufc.quixada.blog.dao.PostDAO;
// import br.ufc.quixada.blog.dao.UserDAO;
import br.ufc.quixada.blog.dao.mongo.CommentDaoMongo;
import br.ufc.quixada.blog.dao.mongo.PostDaoMongo;
import br.ufc.quixada.blog.dao.mongo.UserDaoMongo;
import br.ufc.quixada.blog.models.Comentario;
import br.ufc.quixada.blog.models.Post;
import br.ufc.quixada.blog.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// import br.ufc.quixada.blog.dao.relational.CommentDaoRelacional;
// import br.ufc.quixada.blog.dao.relational.PostDaoRelacional;
// import br.ufc.quixada.blog.dao.relational.UserDaoRelacional;

@RestController
@RequestMapping("/comentarios/mongo")
public class ComentarioMongoController {
    @Autowired
    UserDaoMongo userDAO;

    @Autowired
    PostDaoMongo postDAO;

    @Autowired
    CommentDaoMongo commentDAO;

    @GetMapping("/{id}")
    public ResponseEntity<Comentario> getComentarioById(@PathVariable String id) {
        Optional<Comentario> comentarioOpt = commentDAO.getComentarioById(id);
        if (comentarioOpt.isPresent()) {
            return ResponseEntity.ok(comentarioOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comentario>> getComentariosByPostId(@PathVariable String postId) {
        Optional<Post> postOpt = postDAO.findById(postId);
        if (postOpt.isPresent()) {
            Post post = postOpt.get();
            return ResponseEntity.ok(post.getComentarios());
        } else {
            return ResponseEntity.notFound().build();
        }
        // List<Comentario> comentarios = commentDAO.findByPostId(postId);
        // return;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comentario> createComentario(
            @RequestBody Comentario comentario,
            @RequestParam String idPost,
            @RequestParam String idUsuario) {
        Optional<Post> postOpt = postDAO.findById(idPost);
        Optional<Usuario> usuarioOpt = userDAO.findById(idUsuario);
        if (postOpt.isPresent() && usuarioOpt.isPresent()) {
            comentario.setPost(postOpt.get());
            comentario.setUsuario(usuarioOpt.get());
            Comentario novoComentario = commentDAO.save(comentario);
            return ResponseEntity.ok(novoComentario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comentario> updateComentario(
            @PathVariable String id,
            @RequestBody Comentario comentario,
            @RequestParam String idPost,
            @RequestParam String idUsuario) {
        Optional<Post> postOpt = postDAO.findById(idPost);
        Optional<Usuario> usuarioOpt = userDAO.findById(idUsuario);
        if (postOpt.isPresent() && usuarioOpt.isPresent()) {
            comentario.setId(id);
            comentario.setPost(postOpt.get());
            comentario.setUsuario(usuarioOpt.get());
            Comentario atualizadoComentario = commentDAO.save(comentario);
            if (atualizadoComentario != null) {
                return ResponseEntity.ok(atualizadoComentario);
            } else {
                return ResponseEntity.notFound().build();
            }
        }else{
            return ResponseEntity.notFound().build();
        }
    };

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComentario(@PathVariable String id) {
        Optional<Comentario> commentOpt = commentDAO.findById(id);
        if (commentOpt.isPresent()) {
            Comentario c = commentOpt.get();
            c.setPost(null);
            c.setUsuario(null);
            commentDAO.save(c);
            commentOpt = commentDAO.findById(id);
            if (commentOpt.isPresent()) {
                commentDAO.deleteById(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
