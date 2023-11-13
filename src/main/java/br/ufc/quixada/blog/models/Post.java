package br.ufc.quixada.blog.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "buscarAutorPorIdDePost", query = "select p.usuario from Post p where p.id = :id")
        ,@NamedQuery(name = "buscarComentariosPorIdDoPost", query = "select p.comentarios from Post p where p.id = :id")
})

@Entity
@Table(name = "posts", schema = "public")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post{

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String titulo;

    
    private String corpo;

    @Column(columnDefinition = "float default 0.0")
    private float rate;

    private String categoria;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp dataDeCriacao;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comentario> comentarios;


}
