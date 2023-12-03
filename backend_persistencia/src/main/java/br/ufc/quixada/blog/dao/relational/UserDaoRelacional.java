package br.ufc.quixada.blog.dao.relational;

import br.ufc.quixada.blog.dao.UserDAO;
import br.ufc.quixada.blog.models.Post;
import br.ufc.quixada.blog.models.Usuario;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface UserDaoRelacional extends UserDAO, JpaRepository<Usuario, String> {

    public Usuario findFirstByEmail(String email);

    @Query("select u from Usuario u where u.email = :email")
    public Usuario buscaUsuarioPorEmail(String email);

    // a busca "usuarioPorEmail está definida na Classe User"
    @Query(name = "buscarUsuarioPorEmail")
    public Usuario buscarUsuarioPorEmail(String email);

    @Query(name = "buscarPostsPorIdDeUsuario")
    public List<Post> buscarPostsPorIdDeUsuario(String id);

    @Query("select u from Usuario u where Cast(strftime('%Y', u.dataDeNascimento / 1000, 'unixepoch') as integer) = :ano")
    public List<Usuario> buscarUsuariosPorAnoDeNascimentoSQLITE(int ano);

    // @Query("select u from Usuario u where Extract(year from to_timestamp(to_char(
    // (u.dataDeNascimento),'YYYY-MM-DD'),'YYYY-MM-DD')) = :ano")
    @Query("select u from Usuario u where Extract(year from u.dataDeNascimento) = :ano")
    public List<Usuario> buscarUsuariosPorAnoDeNascimentoPGSQL(int ano);

}
