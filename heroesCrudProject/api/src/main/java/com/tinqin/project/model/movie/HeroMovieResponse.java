package com.tinqin.project.model.movie;


import com.tinqin.project.generics.OperationResult;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class HeroMovieResponse implements OperationResult {
    private String movieName;
    private String releaseDate;
}
