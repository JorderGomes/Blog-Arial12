package br.ufc.quixada.blog.dao;

import java.util.List;
import java.util.Optional;


import br.ufc.quixada.blog.models.Comentario;
import br.ufc.quixada.blog.models.Post;
import br.ufc.quixada.blog.models.Usuario;
import org.springframework.stereotype.Component;

@Component
public interface UserDAO {

    // Crud

    public Usuario save(Usuario usuario);

    public List<Usuario> findAll();

    public Optional<Usuario> findById(String id);

    public void deleteById(String id);

    public boolean existsById(String id);

    public void delete(Usuario usuario);

    // Operações específicas

    public Optional<Usuario> findFirstByEmail(String email);

}
