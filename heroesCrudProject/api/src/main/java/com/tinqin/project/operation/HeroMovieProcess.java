package com.tinqin.project.operation;

import com.tinqin.project.generics.Error;
import com.tinqin.project.generics.OperationProcessor;
import com.tinqin.project.model.movie.HeroMovieRequest;
import com.tinqin.project.model.movie.HeroMovieResponse;
import io.vavr.control.Either;

import java.util.List;

public interface HeroMovieProcess extends OperationProcessor<HeroMovieRequest, HeroMovieResponse> {
    Either<Error, List<HeroMovieResponse>> bigProcess(HeroMovieRequest input);
}
