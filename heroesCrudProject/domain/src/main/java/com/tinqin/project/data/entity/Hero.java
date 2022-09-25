package com.tinqin.project.data.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="heroes")
@Getter
@Setter
public class Hero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)



    private Long idHero;

    private String heroName;

    private String heroAge;

    private String heroGender;

    private String alignment;

    @OneToOne
    @JoinColumn(name = "idAttribute" )
    private HeroAttribute heroAttribute;

    @ManyToOne
    @JoinColumn(name = "idHeroTypes")
    private HeroType heroType;

    @OneToMany(mappedBy = "hero")
    private Set<MoviesOfTheHero>  heroMovies= new HashSet<>();

    public Hero() {
    }

    public Hero(Long idHero, String heroName, String heroAge, String heroGender, String alignment, HeroAttribute heroAttribute, HeroType heroType) {
        this.idHero = idHero;
        this.heroName = heroName;
        this.heroAge = heroAge;
        this.heroGender = heroGender;
        this.alignment = alignment;
        this.heroAttribute = heroAttribute;
        this.heroType = heroType;
    }

    public Hero(Long idHero, String heroName) {
        this.idHero = idHero;
        this.heroName = heroName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hero hero = (Hero) o;
        return Objects.equals(idHero, hero.idHero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idHero);
    }
}
