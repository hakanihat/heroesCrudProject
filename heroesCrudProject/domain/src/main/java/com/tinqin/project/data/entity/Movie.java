package com.tinqin.project.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Movies")
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



}
