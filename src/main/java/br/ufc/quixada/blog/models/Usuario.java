package br.ufc.quixada.blog.models;

import java.io.Serializable;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import jakarta.persistence.*;

@NamedQueries({
        @NamedQuery(name = "usuarioPorEmail", query = "select u from Usuario u where u.email = :email")
})

@Entity
@Table(name = "usuarios", schema = "public")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String name;

    @Column(nullable = false)
    private String password;
    
    @Column(unique = true,nullable = false)
    private String email;
    
    private String bio;
    private Double rate;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private Collection<Post> posts;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private Collection<Comentario> comentarios;

    @Override
    public String toString() {
        return "{ name: \""+ name + "\", password: \"" + password + "\", email: \"" + email + "\", bio: \"" + bio + "\", rate: \"" + rate + "\"}\n";
    }
}