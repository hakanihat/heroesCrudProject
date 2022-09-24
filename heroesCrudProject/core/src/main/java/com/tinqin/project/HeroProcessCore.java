package com.tinqin.project;

import com.tinqin.project.data.entity.Hero;
import com.tinqin.project.data.repository.HeroRepository;
import com.tinqin.project.error.general.GeneralServerError;
import com.tinqin.project.error.hero.NoSuchHeroError;
import com.tinqin.project.exception.hero.HeroNotFoundException;
import com.tinqin.project.generics.Error;
import com.tinqin.project.model.appearance.HeroRequest;
import com.tinqin.project.model.appearance.HeroResponse;
import com.tinqin.project.operation.HeroProcess;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

@Service
public class HeroProcessCore implements HeroProcess {
    private final HeroRepository heroRepository;

    public HeroProcessCore(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    @Override
    public Either<Error, HeroResponse> process(HeroRequest input) {
        return Try.of(()->{
            final Hero hero = heroRepository.findById(input.getHeroId()) //TODO cant get the info from repo , need to fix
                    .orElseThrow(HeroNotFoundException::new);
            return HeroResponse.builder()
                    .heroId(hero.getIdHero())
                    .heroName(hero.getHeroName())
                    .heroAge(hero.getHeroAge())
                    .heroGender(hero.getHeroGender())
                    .heroType(hero.getHeroType().getTypeName())
                    .alignment(hero.getAlignment())
                    .combat(hero.getHeroAttribute().getCombat().toString())
                    .intelligence(hero.getHeroAttribute().getIntelligence().toString())
                    .durability(hero.getHeroAttribute().getDurability().toString())
                    .power(hero.getHeroAttribute().getPower().toString())
                    .speed(hero.getHeroAttribute().getSpeed().toString())
                    .strength(hero.getHeroAttribute().getStrength().toString())
                    .build();
        }).toEither()
                .mapLeft(throwable -> {
                    if(throwable instanceof HeroNotFoundException)
                        return new NoSuchHeroError();
                    return new GeneralServerError();
                });
    }
}
