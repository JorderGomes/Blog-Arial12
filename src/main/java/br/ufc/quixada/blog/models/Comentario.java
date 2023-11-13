package br.ufc.quixada.blog.models;


import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comentarios", schema = "public")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String corpo;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp dataDeCriacao;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Post post;

    @ManyToOne(cascade = CascadeType.REMOVE)
    // @JsonIgnore
    private Usuario usuario;
}