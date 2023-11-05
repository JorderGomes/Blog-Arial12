package br.ufc.quixada.blog.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "posts", schema = "projetoDSP")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Post{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comentario> comentarios;


}
