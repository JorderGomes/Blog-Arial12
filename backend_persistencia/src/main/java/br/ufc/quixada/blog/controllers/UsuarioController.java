package br.ufc.quixada.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.ufc.quixada.blog.dao.UserDAO;
import br.ufc.quixada.blog.models.Post;
import br.ufc.quixada.blog.models.Usuario;
import br.ufc.quixada.blog.service.UserService;

// import br.ufc.quixada.blog.dao.relational.UserDaoRelacional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    UserDAO userDAO;

    @Autowired
    UserService userService;

    @Value("${spring.profiles.active}")
    private String databaseProfile;

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios(){
        return ResponseEntity.ok(userDAO.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable String id) {
        return userDAO.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/")
    public ResponseEntity<Usuario> getUsuarioByEmail(@RequestParam String email) {
        return userDAO.findFirstByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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
        //        userService.deleteWithDependences(id);
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
