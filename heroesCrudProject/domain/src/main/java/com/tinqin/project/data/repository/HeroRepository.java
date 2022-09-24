package com.tinqin.project.data.repository;

import com.tinqin.project.data.entity.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroRepository extends JpaRepository<Hero,Long> {
   // Hero getHeroByHeroName(String heroName);
}
