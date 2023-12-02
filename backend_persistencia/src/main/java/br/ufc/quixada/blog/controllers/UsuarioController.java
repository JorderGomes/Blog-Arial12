package br.ufc.quixada.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.quixada.blog.dao.UserDAO;
import br.ufc.quixada.blog.models.Post;
import br.ufc.quixada.blog.models.Usuario;

// import br.ufc.quixada.blog.dao.relational.UserDaoRelacional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    UserDAO userDAO;

    @Value("${spring.profiles.active}")
    private String databaseProfile;

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios(){
        return ResponseEntity.ok(userDAO.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable String id){
        return userDAO.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/anoNascimento/{anoNascimento}")
    public ResponseEntity<List<Usuario>> getUsuariosByAnoDeNascimento(@PathVariable Integer anoNascimento){
        if(databaseProfile.equals("sqlite"))
            return ResponseEntity.ok(userDAO.buscarUsuariosPorAnoDeNascimentoSQLITE(anoNascimento));
        else
            return ResponseEntity.ok(userDAO.buscarUsuariosPorAnoDeNascimentoPGSQL(anoNascimento));
    }

    @GetMapping(value = "/{id}/posts")
    public ResponseEntity<List<Post>> getPostsByUsuario(@PathVariable String id){
        return ResponseEntity.ok(userDAO.buscarPostsPorIdDeUsuario(id)); 
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario postUsuario(@RequestBody Usuario usuario){
        usuario.setRate((double) 0);
        return userDAO.save(usuario);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable String id){
        if (!userDAO.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        userDAO.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario, @PathVariable String id){
        if (!userDAO.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        usuario.setId(id);
        usuario = userDAO.save(usuario);
        return  ResponseEntity.ok(usuario);
    }

}
