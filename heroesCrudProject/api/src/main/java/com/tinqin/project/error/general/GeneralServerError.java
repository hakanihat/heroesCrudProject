package com.tinqin.project.error.general;

import com.tinqin.project.generics.Error;
import org.springframework.http.HttpStatus;

public class GeneralServerError implements Error {
    @Override
    public String getMessage() {
        return "Unhandled exceptions!";
    }

    @Override
    public HttpStatus getCode() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
