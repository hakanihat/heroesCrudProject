package com.tinqin.project;

import com.tinqin.project.data.entity.Hero;
import com.tinqin.project.data.entity.Movie;
import com.tinqin.project.data.entity.MoviesOfTheHero;
import com.tinqin.project.data.repository.HeroRepository;
import com.tinqin.project.data.repository.MovieRepository;
import com.tinqin.project.data.repository.MoviesOfTheHeroRepository;
import com.tinqin.project.error.general.GeneralServerError;
import com.tinqin.project.error.hero.NoSuchHeroError;
import com.tinqin.project.exception.hero.HeroNotFoundException;
import com.tinqin.project.generics.Error;
import com.tinqin.project.model.appearance.HeroResponse;
import com.tinqin.project.model.fight.HeroFightResponse;
import com.tinqin.project.model.movie.HeroMovieRequest;
import com.tinqin.project.model.movie.HeroMovieResponse;
import com.tinqin.project.operation.HeroMovieProcess;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroMovieProcessCore implements HeroMovieProcess {

    private final HeroRepository heroRepository;
    private final MoviesOfTheHeroRepository moviesOfTheHeroRepository;

    private final MovieRepository movieRepository;

    public HeroMovieProcessCore(HeroRepository heroRepository, MoviesOfTheHeroRepository moviesOfTheHeroRepository, MovieRepository movieRepository) {
        this.heroRepository = heroRepository;
        this.moviesOfTheHeroRepository = moviesOfTheHeroRepository;
        this.movieRepository = movieRepository;
    }


    @Override
    public Either<Error, List<HeroMovieResponse>> bigProcess(HeroMovieRequest input) {
        return Try.of(()->{
                    final Hero hero = heroRepository.findById(input.getHeroId())
                            .orElseThrow(HeroNotFoundException::new);
                    final List<MoviesOfTheHero> moviesOfTheHero = moviesOfTheHeroRepository.getMoviesOfTheHeroByHero_IdHero(hero.getIdHero());

                    List<HeroMovieResponse> movies= moviesOfTheHero.stream()
                            .filter(moviesOfTheHero1 -> moviesOfTheHero1.getHero().getIdHero()==hero.getIdHero()) //no need actually
                            .map(moviesOfTheHero1 ->  movieRepository.findById(moviesOfTheHero1.getMovie().getIdMovie()).orElseThrow())
                            .map(movie -> HeroMovieResponse.builder()
                                    .movieName(movie.getMovieName())
                                    .releaseDate(String.valueOf(movie.getReleaseDate())).build())
                            .toList();

                    return movies;

                }).toEither()
                .mapLeft(throwable -> {
                    if(throwable instanceof HeroNotFoundException)
                        return new NoSuchHeroError();
                    return new GeneralServerError();
                });
    }

    @Override
    public Either<Error, HeroMovieResponse> process(HeroMovieRequest input) {
        return null;
    }
}
