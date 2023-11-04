package br.ufc.quixada.blog.models;

import java.io.Serializable;
import java.util.Collection;

import lombok.*;

import jakarta.persistence.*;

@NamedQueries({
        @NamedQuery(name = "usuarioPorEmail", query = "select u from Usuario u where u.email = :email")
})

@Entity
@Table(name = "usuarios", schema = "projetoDSP")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private String id;
    
    private String name;

    @Column(nullable = false)
    private String password;
    
    @Column(unique = true,nullable = false)
    private String email;
    
    private String bio;
    private Double rate;

    @OneToMany(mappedBy = "usuario")
    private Collection<Post> posts;

    @OneToMany(mappedBy = "usuario")
    private Collection<Comentario> comentarios;

    @Override
    public String toString() {
        return "{ name: \""+ name + "\", password: \"" + password + "\", email: \"" + email + "\", bio: \"" + bio + "\", rate: \"" + rate + "\"}\n";
    }
}