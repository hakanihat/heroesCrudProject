package com.tinqin.project.model.movie;


import com.tinqin.project.generics.OperationResult;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class HeroMovieResponse implements OperationResult {
    private String movieName;
    private String releaseDate;
}
