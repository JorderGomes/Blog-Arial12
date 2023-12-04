package br.ufc.quixada.blog.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
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
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

    private String titulo;
    
    private String corpo;

    @Column(columnDefinition = "numeric default 0.0")
    private Double rate;

    private String categoria;

    @Temporal(TemporalType.DATE)
    private Date dataDeCriacao;


    @ManyToOne
    private Usuario usuario;

    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comentario> comentarios;


}
