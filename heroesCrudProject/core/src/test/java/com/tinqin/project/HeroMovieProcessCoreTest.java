package com.tinqin.project;

import com.tinqin.project.data.entity.*;
import com.tinqin.project.data.repository.HeroRepository;
import com.tinqin.project.data.repository.MovieRepository;
import com.tinqin.project.data.repository.MoviesOfTheHeroRepository;
import com.tinqin.project.model.movie.HeroMovieRequest;
import com.tinqin.project.model.movie.HeroMovieResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class HeroMovieProcessCoreTest {
    @Mock
    private HeroRepository heroRepository;
    @Mock
    private MoviesOfTheHeroRepository moviesOfTheHeroRepository;
    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private HeroMovieProcessCore heroMovieProcessCore;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcess(){
        final HeroType heroType = new HeroType(1L,"melee");
        final HeroAttribute heroAttribute = new HeroAttribute(1L,38,100,17,80,64,24);
        final Hero hero = new Hero(1L,"A-Bomb","unknown","male","good",heroAttribute,heroType);
        final Movie movie = new Movie(1L,"Avengers: Infinity War",LocalDate.of(2018,4,23));
        final Movie movie2 = new Movie(2L,"Avengers: Endgame",LocalDate.of(2019,4,26));
        final MoviesOfTheHero moviesOfTheHero = new MoviesOfTheHero(1L,hero,movie);
        final MoviesOfTheHero moviesOfTheHero2 = new MoviesOfTheHero(2L,hero,movie2);
        HeroMovieRequest heroMovieRequest = HeroMovieRequest.builder()
                .heroId(1L)
                .build();

        HeroMovieResponse heroMovieResponse = HeroMovieResponse.builder()
                .movieName("Avengers: Infinity War")
                .releaseDate("2018-04-23")
                .build();
        HeroMovieResponse heroMovieResponse2 = HeroMovieResponse.builder()
                .movieName("Avengers: Endgame")
                .releaseDate("2019-04-26")
                .build();

        List<HeroMovieResponse> heroMovieResponses = new ArrayList<>();
        heroMovieResponses.add(heroMovieResponse);
        heroMovieResponses.add(heroMovieResponse2);

        when(heroRepository.findById(1L)).thenReturn(Optional.of(hero));
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        when(movieRepository.findById(2L)).thenReturn(Optional.of(movie2));
        when(moviesOfTheHeroRepository.getMoviesOfTheHeroByHero_IdHero(1L)).thenReturn(List.of(moviesOfTheHero,moviesOfTheHero2));



        Assertions.assertEquals(heroMovieResponses,heroMovieProcessCore.bigProcess(heroMovieRequest).get());

    }
}
