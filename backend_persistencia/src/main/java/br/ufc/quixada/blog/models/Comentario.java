package br.ufc.quixada.blog.models;


import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comentarios", schema = "public")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String corpo;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp dataDeCriacao;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Post post;


    @ManyToOne(cascade = CascadeType.REMOVE)
    private Usuario usuario;
}