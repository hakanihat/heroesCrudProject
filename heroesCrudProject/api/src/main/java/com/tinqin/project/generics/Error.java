package com.tinqin.project.generics;

import org.springframework.http.HttpStatus;

public interface Error {
    String getMessage();
    HttpStatus getCode();
}
