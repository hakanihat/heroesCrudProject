package com.tinqin.project.data.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "movies_of_the_heroes")
@Getter
@Setter

public class MoviesOfTheHero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idMoviesOfTheHero;

    @ManyToOne
    @JoinColumn(name = "idHero")
    private Hero hero;

    @ManyToOne
    @JoinColumn(name="idMovie")
    private Movie movie;

    public MoviesOfTheHero() {
    }

}
