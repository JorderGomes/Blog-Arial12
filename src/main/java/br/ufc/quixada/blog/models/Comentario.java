package br.ufc.quixada.blog.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comentarios", schema = "projetoDSP")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private Post post;

    @ManyToOne
    private Usuario usuario;
}