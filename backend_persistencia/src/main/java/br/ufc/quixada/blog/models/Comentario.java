package br.ufc.quixada.blog.models;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
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
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

    private String corpo;

    @Temporal(TemporalType.DATE)
    private Date dataDeCriacao;


    @ManyToOne
    private Post post;

    @ManyToOne
    private Usuario usuario;
}