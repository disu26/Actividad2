package co.com.sofka.back_crud_fullstack.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "todos")
public final class TodoDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String text;

    private Boolean completado;
}
