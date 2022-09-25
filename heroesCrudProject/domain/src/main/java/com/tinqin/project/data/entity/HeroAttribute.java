package com.tinqin.project.data.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "hero_attributes")
@Getter
@Setter
public class HeroAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idAttribute;

    private Integer intelligence;

    private Integer strength;

    private Integer speed;

    private Integer durability;

    private Integer combat ;

    private Integer power;

    @OneToOne(mappedBy = "heroAttribute")
    private Hero hero;

    public HeroAttribute() {
    }

    public HeroAttribute(Long idAttribute, Integer intelligence, Integer strength, Integer speed, Integer durability, Integer combat, Integer power) {
        this.idAttribute = idAttribute;
        this.intelligence = intelligence;
        this.strength = strength;
        this.speed = speed;
        this.durability = durability;
        this.combat = combat;
        this.power = power;
    }
}
