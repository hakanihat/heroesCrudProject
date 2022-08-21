package com.tinqin.project.data.repository;

import com.tinqin.project.data.entity.MoviesOfTheHero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesOfTheHeroRepository extends JpaRepository<MoviesOfTheHero,Long> {

    List<MoviesOfTheHero> getMoviesOfTheHeroByHero_IdHero(Long idHero);
}
