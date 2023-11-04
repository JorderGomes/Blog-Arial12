package br.ufc.quixada.blog.dao;

import br.ufc.quixada.blog.models.Comentario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDAO extends JpaRepository<Comentario, Integer> {


}
