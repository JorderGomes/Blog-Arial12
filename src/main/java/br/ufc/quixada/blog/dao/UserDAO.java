package br.ufc.quixada.blog.dao;

import br.ufc.quixada.blog.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO  extends JpaRepository<Usuario, Integer> {

    public Usuario findFirstByEmail(String email);

    @Query("select u from Usuario u where u.email = :email")
    public Usuario buscaUsuarioPorEmail(String email);



    // a busca "usuarioPorEmail está definida na Classe User"
    @Query(name = "usuarioPorEmail")
    public Usuario buscaNomeadaDeUsuarioPorEmail(String email);



}
