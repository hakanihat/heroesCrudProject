package com.tinqin.project;

import com.tinqin.project.data.entity.Hero;
import com.tinqin.project.data.repository.HeroRepository;
import com.tinqin.project.error.general.GeneralServerError;
import com.tinqin.project.error.hero.NoSuchHeroError;
import com.tinqin.project.exception.hero.HeroNotFoundException;
import com.tinqin.project.generics.Error;
import com.tinqin.project.model.HeroAppearanceRequest;
import com.tinqin.project.model.HeroAppearanceResponse;
import com.tinqin.project.operation.HeroAppearanceProcess;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

@Service
public class HeroAppearanceProcessCore implements HeroAppearanceProcess {
    private final HeroRepository heroRepository;

    public HeroAppearanceProcessCore(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    @Override
    public Either<Error, HeroAppearanceResponse> process(HeroAppearanceRequest input) {
        return Try.of(()->{
            final Hero hero = heroRepository.findById(input.getHeroId())
                    .orElseThrow(HeroNotFoundException::new);
            return HeroAppearanceResponse.builder()
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
