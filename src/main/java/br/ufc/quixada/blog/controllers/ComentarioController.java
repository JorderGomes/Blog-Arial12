package br.ufc.quixada.blog.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.quixada.blog.dao.CommentDAO;
import br.ufc.quixada.blog.dao.PostDAO;
import br.ufc.quixada.blog.dao.UserDAO;
import br.ufc.quixada.blog.models.Comentario;
import br.ufc.quixada.blog.models.Post;
import br.ufc.quixada.blog.models.Usuario;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {
    @Autowired
    UserDAO userDAO;

    @Autowired
    PostDAO postDAO;

    @Autowired
    CommentDAO commentDAO;

    @GetMapping("/{id}")
    public ResponseEntity<Comentario> getComentarioById(@PathVariable Integer id) {
        Optional<Comentario> comentarioOpt = commentDAO.findById(id);
        if (comentarioOpt.isPresent()) {
            return ResponseEntity.ok(comentarioOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comentario>> getComentariosByPostId(@PathVariable Integer postId) {
        Optional<Post> postOpt = postDAO.findById(postId);
        if (postOpt.isPresent()){
            Post post = postOpt.get();
            return  ResponseEntity.ok(post.getComentarios());
        } else {
            return ResponseEntity.notFound().build();
        }
        // List<Comentario> comentarios = commentDAO.findByPostId(postId);
        // return;
    }

    @PostMapping
    public ResponseEntity<Comentario> createComentario(
        @RequestBody Comentario comentario, 
        @RequestParam int idPost, 
        @RequestParam int idUsuario
        ) {
        Optional<Post> postOpt = postDAO.findById(idPost);
        Optional<Usuario> usuarioOpt = userDAO.findById(idUsuario);
        if (postOpt.isPresent() && usuarioOpt.isPresent()){
            comentario.setPost(postOpt.get());
            comentario.setUsuario(usuarioOpt.get());
            Comentario novoComentario = commentDAO.save(comentario);
            return ResponseEntity.ok(novoComentario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comentario> updateComentario(@PathVariable Integer id, @RequestBody Comentario comentario) {
        comentario.setId(id);
        Comentario atualizadoComentario = commentDAO.save(comentario);
        if (atualizadoComentario != null) {
            return ResponseEntity.ok(atualizadoComentario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComentario(@PathVariable Integer id) {
        Optional<Comentario> commentOpt = commentDAO.findById(id);
        if (commentOpt.isPresent()) {
            Comentario c = commentOpt.get();
            c.setPost(null);
            c.setUsuario(null);
            commentDAO.delete(c);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
