package br.ufc.quixada.blog.controllers.mongo;

import br.ufc.quixada.blog.dao.UserDAO;
import br.ufc.quixada.blog.dao.mongo.UserDaoMongo;
import br.ufc.quixada.blog.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// import br.ufc.quixada.blog.dao.relational.UserDaoRelacional;

@RestController
@RequestMapping("/usuarios/mongo")
public class UsuarioMongoController {

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

    @GetMapping(value = "/email")
    public ResponseEntity<Usuario> buscarPorEmail(@RequestParam String email){
        System.out.println(email);
        Optional<Usuario> optUsuario = userDAO.findFirstByEmail(email);
        if (optUsuario.isPresent()){
            return ResponseEntity.ok(optUsuario.get());
        }
        System.out.println();
        return ResponseEntity.notFound().build();
    }

}
