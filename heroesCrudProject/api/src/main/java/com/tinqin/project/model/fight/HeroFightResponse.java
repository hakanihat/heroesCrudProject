package com.tinqin.project.model.fight;

import com.tinqin.project.generics.OperationResult;
import lombok.*;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class HeroFightResponse implements OperationResult {
    private String winnerName;
    private String pointDifference;
}
