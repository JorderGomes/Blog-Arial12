package br.ufc.quixada.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.quixada.blog.dao.UserDAO;
import br.ufc.quixada.blog.models.Usuario;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    UserDAO userDAO;

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios(){
        return ResponseEntity.ok(userDAO.findAll());
    }
}
