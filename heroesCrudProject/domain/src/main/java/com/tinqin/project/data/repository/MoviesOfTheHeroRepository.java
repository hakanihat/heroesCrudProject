package com.tinqin.project.data.repository;

import com.tinqin.project.data.entity.MoviesOfTheHero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesOfTheHeroRepository extends JpaRepository<MoviesOfTheHero,Long> {

    MoviesOfTheHero getMoviesOfTheHeroByHero_IdHero(Long idHero);
}
