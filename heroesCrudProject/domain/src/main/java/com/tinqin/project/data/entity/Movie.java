package com.tinqin.project.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "movies")
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idMovie;

    private String movieName;

    private LocalDate releaseDate;

    @OneToMany(mappedBy = "movie")
    private Set<MoviesOfTheHero> moviesOfTheHero = new HashSet<>();

    public Movie() {
    }


    public Movie(Long idMovie, String movieName, LocalDate releaseDate) {
        this.idMovie = idMovie;
        this.movieName = movieName;
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(idMovie, movie.idMovie);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "idMovie=" + idMovie +
                ", movieName='" + movieName + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMovie);
    }
}
