package com.tinqin.project.rest.controller;

import com.tinqin.project.generics.Error;
import com.tinqin.project.model.appearance.HeroRequest;
import com.tinqin.project.model.appearance.HeroResponse;
import com.tinqin.project.operation.HeroProcess;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeroController {
private final HeroProcess heroProcess;

    public HeroController(HeroProcess heroProcess) {
        this.heroProcess = heroProcess;
    }

    @PostMapping("/getHeroFromDB")
    public ResponseEntity<?> getHeroFromDB(@RequestBody HeroRequest heroRequest) {
        Either<Error, HeroResponse> response = heroProcess.process(heroRequest);
        if(response.isLeft()){
            return  ResponseEntity.status(response.getLeft().getCode()).body(response.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }
}
