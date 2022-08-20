package com.tinqin.project.model.appearance;


import com.tinqin.project.generics.OperationResult;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class HeroResponse implements OperationResult {
    private Long heroId;

    private String heroName;

    private String heroAge;

    private String heroGender;

    private String alignment;

    private String intelligence;

    private String power;

    private String strength;

    private String speed;

    private String combat;

    private String durability;

    private String heroType;

}
