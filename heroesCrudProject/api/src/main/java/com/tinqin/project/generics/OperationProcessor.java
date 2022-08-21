package com.tinqin.project.generics;

import io.vavr.control.Either;

import java.util.List;

public interface OperationProcessor  <I extends OperationInput, R extends OperationResult>{

    Either<Error,R>   process(I input);
}
