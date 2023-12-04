package br.ufc.quixada.blog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.quixada.blog.dao.CommentDAO;
import br.ufc.quixada.blog.dao.PostDAO;
import br.ufc.quixada.blog.dao.UserDAO;
import br.ufc.quixada.blog.models.Usuario;

@Service
public class UserService {
    @Autowired
    private UserDAO userRepository;

    @Autowired
    private PostDAO postRepository;

    @Autowired
    private CommentDAO commentDAO;

    // Método para remover um usuário e seus posts associados
    public void deleteWithDependences(String userId) {
        Optional<Usuario> user = userRepository.findById(userId);

        if (user.isPresent()) {
            // Remove os posts associados ao usuário
            postRepository.deleteByUserId(userId);

            // Remove os comentários associados ao usuário
            commentDAO.deleteByUserId(userId);

            // Remove o próprio usuário
            userRepository.deleteById(userId);
        }
    }
}
