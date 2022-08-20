package com.tinqin.project.rest.controller;

import com.tinqin.project.generics.Error;
import com.tinqin.project.model.HeroAppearanceRequest;
import com.tinqin.project.model.HeroAppearanceResponse;
import com.tinqin.project.operation.HeroAppearanceProcess;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeroController {
private final HeroAppearanceProcess heroAppearanceProcess;

    public HeroController(HeroAppearanceProcess heroAppearanceProcess) {
        this.heroAppearanceProcess = heroAppearanceProcess;
    }

    @PostMapping("/getHeroFromDB")
    public ResponseEntity<?> getHeroFromDB(@RequestBody HeroAppearanceRequest heroAppearanceRequest) {
        Either<Error, HeroAppearanceResponse> response = heroAppearanceProcess.process(heroAppearanceRequest);
        if(response.isLeft()){
            return  ResponseEntity.status(response.getLeft().getCode()).body(response.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }
}
