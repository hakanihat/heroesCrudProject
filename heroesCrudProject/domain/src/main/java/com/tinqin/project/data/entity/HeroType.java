package com.tinqin.project.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name ="HeroTypes")

@Getter
@Setter

public class HeroType {
    @Id
    @GeneratedValue
    private Long idHeroTypes;

    private String typeName;

    @OneToMany(mappedBy = "heroType")
    private Set<Hero> heroes;

    public HeroType() {
    }


}
