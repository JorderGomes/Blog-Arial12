package br.ufc.quixada.blog.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


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
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;

    // @Lob
    // @Column(length = 1000)
    private String corpo;

    @Column(columnDefinition = "float default 0.0")
    private float rate;

    private String categoria;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comentario> comentarios;


}
