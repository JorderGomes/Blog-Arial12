package br.ufc.quixada.blog.dao;

import java.util.List;
import java.util.Optional;

import br.ufc.quixada.blog.models.Post;
import br.ufc.quixada.blog.models.Usuario;

public interface UserDAO {

    // Crud

    public Usuario save(Usuario usuario);

    public List<Usuario> findAll();

    public Optional<Usuario> findById(String id);

    public void deleteById(String id);

    public boolean existsById(String id);

    // Operações específicas

    public Usuario findFirstByEmail(String email);

    public Usuario buscaUsuarioPorEmail(String email);

    public Usuario buscarUsuarioPorEmail(String email);

    public List<Post> buscarPostsPorIdDeUsuario(String id);

    public List<Usuario> buscarUsuariosPorAnoDeNascimentoSQLITE(int ano);

    public List<Usuario> buscarUsuariosPorAnoDeNascimentoPGSQL(int ano);

}
