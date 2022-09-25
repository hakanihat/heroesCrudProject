package com.tinqin.project.model.movie;

import com.tinqin.project.generics.OperationInput;
import lombok.*;

@Setter(AccessLevel.PRIVATE)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HeroMovieRequest implements OperationInput {
    private Long heroId;
}
