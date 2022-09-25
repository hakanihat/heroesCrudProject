package com.tinqin.project.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name ="hero_types")

@Getter
@Setter

public class HeroType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idHeroTypes;

    private String typeName;

    @OneToMany(mappedBy = "heroType")
    private Set<Hero> heroes;

    public HeroType() {
    }

    public HeroType(Long idHeroTypes, String typeName) {
        this.idHeroTypes = idHeroTypes;
        this.typeName = typeName;
    }
}
