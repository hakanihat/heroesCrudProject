package com.tinqin.project.data.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

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

    public MoviesOfTheHero(Long idMoviesOfTheHero, Hero hero, Movie movie) {
        this.idMoviesOfTheHero = idMoviesOfTheHero;
        this.hero = hero;
        this.movie = movie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoviesOfTheHero that = (MoviesOfTheHero) o;
        return Objects.equals(idMoviesOfTheHero, that.idMoviesOfTheHero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMoviesOfTheHero);
    }
}
