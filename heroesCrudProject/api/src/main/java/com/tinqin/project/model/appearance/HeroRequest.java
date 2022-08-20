package com.tinqin.project.model.appearance;

import com.tinqin.project.generics.OperationInput;
import lombok.*;

@Setter(AccessLevel.PRIVATE)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HeroRequest implements OperationInput {
    private Long heroId;
}
