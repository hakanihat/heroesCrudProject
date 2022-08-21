package com.tinqin.project.model.fight;

import com.tinqin.project.generics.OperationInput;
import jdk.dynalink.Operation;
import lombok.*;

@Setter(AccessLevel.PRIVATE)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HeroFightRequest implements OperationInput {
    private Long firstHeroId;
    private Long secondHeroId;
}
