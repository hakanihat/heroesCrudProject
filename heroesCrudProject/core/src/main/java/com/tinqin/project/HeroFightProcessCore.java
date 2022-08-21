package com.tinqin.project;

import com.tinqin.project.data.entity.Hero;
import com.tinqin.project.data.repository.HeroRepository;
import com.tinqin.project.error.general.GeneralServerError;
import com.tinqin.project.error.hero.NoSuchHeroError;
import com.tinqin.project.exception.hero.HeroNotFoundException;
import com.tinqin.project.generics.Error;
import com.tinqin.project.model.fight.HeroFightRequest;
import com.tinqin.project.model.fight.HeroFightResponse;
import com.tinqin.project.operation.HeroFightProcess;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

@Service
public class HeroFightProcessCore implements HeroFightProcess {
    private final HeroRepository heroRepository;

    public HeroFightProcessCore(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }


    private Integer sumStats(Hero hero)
    {
        return  hero.getHeroAttribute().getCombat()+
                hero.getHeroAttribute().getDurability()+
                hero.getHeroAttribute().getIntelligence()+
                hero.getHeroAttribute().getPower()+
                hero.getHeroAttribute().getSpeed()+
                hero.getHeroAttribute().getStrength();
    }

    @Override
    public Either<Error, HeroFightResponse> process(HeroFightRequest input) {
        return Try.of(()->{
                    final Hero firstHero = heroRepository.findById(input.getFirstHeroId())
                            .orElseThrow(HeroNotFoundException::new);

                    final Hero secondHero = heroRepository.findById(input.getSecondHeroId())
                            .orElseThrow(HeroNotFoundException::new);

                    if(sumStats(firstHero) > sumStats(secondHero)){
                        return HeroFightResponse.builder()
                                .winnerName(firstHero.getHeroName())
                                .pointDifference(String.valueOf(sumStats(firstHero) - sumStats(secondHero)))
                                .build();
                    }
                    return HeroFightResponse.builder()
                            .winnerName(secondHero.getHeroName())
                            .pointDifference(String.valueOf(sumStats(secondHero) - sumStats(firstHero)))
                            .build();

                }).toEither()
                .mapLeft(throwable -> {
                    if(throwable instanceof HeroNotFoundException)
                        return new NoSuchHeroError();
                    return new GeneralServerError();
                });
    }
}
