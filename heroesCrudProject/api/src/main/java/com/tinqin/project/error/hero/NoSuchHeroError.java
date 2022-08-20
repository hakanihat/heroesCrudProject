package com.tinqin.project.error.hero;

import com.tinqin.project.generics.Error;
import org.springframework.http.HttpStatus;

public class NoSuchHeroError implements Error {
    @Override
    public String getMessage() {
        return "No such hero exists!";
    }

    @Override
    public HttpStatus getCode() {
        return HttpStatus.NOT_FOUND;
    }
}
