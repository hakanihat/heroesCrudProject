package com.tinqin.project.model.fight;

import com.tinqin.project.generics.OperationResult;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class HeroFightResponse implements OperationResult {
    private String winnerName;
    private String pointDifference;
}
