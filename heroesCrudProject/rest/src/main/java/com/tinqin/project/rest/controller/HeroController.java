package com.tinqin.project.rest.controller;

import com.tinqin.project.generics.Error;
import com.tinqin.project.model.appearance.HeroRequest;
import com.tinqin.project.model.appearance.HeroResponse;
import com.tinqin.project.model.fight.HeroFightRequest;
import com.tinqin.project.model.fight.HeroFightResponse;
import com.tinqin.project.model.movie.HeroMovieRequest;
import com.tinqin.project.model.movie.HeroMovieResponse;
import com.tinqin.project.operation.HeroFightProcess;
import com.tinqin.project.operation.HeroMovieProcess;
import com.tinqin.project.operation.HeroProcess;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HeroController {
private final HeroProcess heroProcess;
private final HeroFightProcess heroFightProcess;
private final HeroMovieProcess heroMovieProcess;

    public HeroController(HeroProcess heroProcess, HeroFightProcess heroFightProcess, HeroMovieProcess heroMovieProcess) {
        this.heroProcess = heroProcess;
        this.heroFightProcess = heroFightProcess;
        this.heroMovieProcess = heroMovieProcess;
    }

    @PostMapping("/getHeroFromDB")
    public ResponseEntity<?> getHeroFromDB(@RequestBody HeroRequest heroRequest) {
        Either<Error, HeroResponse> response = heroProcess.process(heroRequest);
        if(response.isLeft()){
            return  ResponseEntity.status(response.getLeft().getCode()).body(response.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }

    @PostMapping("/getFightResult")
    public ResponseEntity<?> getFightResult(@RequestBody HeroFightRequest heroFightRequest)
    {
        Either<Error, HeroFightResponse> response = heroFightProcess.process(heroFightRequest);
        if(response.isLeft()){
            return  ResponseEntity.status(response.getLeft().getCode()).body(response.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }

    @PostMapping("/getHeroMovies")
    public ResponseEntity<?> getHeroMovies(@RequestBody HeroMovieRequest heroMovieRequest)
    {
        Either<Error, List<HeroMovieResponse>> response = heroMovieProcess.bigProcess(heroMovieRequest);
        if(response.isLeft()){
            return  ResponseEntity.status(response.getLeft().getCode()).body(response.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }
}
