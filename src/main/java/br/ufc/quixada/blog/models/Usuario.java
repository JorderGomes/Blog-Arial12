package br.ufc.quixada.blog.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import jakarta.persistence.*;

@NamedQueries({
        @NamedQuery(name = "buscarUsuarioPorEmail", query = "select u from Usuario u where u.email = :email")
        ,@NamedQuery(name = "buscarPostsPorIdDeUsuario", query = "select u.posts from Usuario u where u.id = :id")
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
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    private String name;

    @Column(nullable = false)
    private String password;
    
    @Column(unique = true,nullable = false)
    private String email;
    
    private String bio;

    @Column(columnDefinition = "double default 0.0")
    private Double rate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataDeNascimento;

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