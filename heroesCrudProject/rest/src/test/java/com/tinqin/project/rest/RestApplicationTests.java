package com.tinqin.project.rest;

import com.tinqin.project.HeroFightProcessCore;
import com.tinqin.project.HeroMovieProcessCore;
import com.tinqin.project.HeroProcessCore;
import com.tinqin.project.data.entity.*;
import com.tinqin.project.data.repository.HeroRepository;
import com.tinqin.project.data.repository.MovieRepository;
import com.tinqin.project.data.repository.MoviesOfTheHeroRepository;
import com.tinqin.project.model.appearance.HeroRequest;
import com.tinqin.project.model.appearance.HeroResponse;
import com.tinqin.project.model.fight.HeroFightRequest;
import com.tinqin.project.model.fight.HeroFightResponse;
import com.tinqin.project.model.movie.HeroMovieRequest;
import com.tinqin.project.model.movie.HeroMovieResponse;
import com.tinqin.project.rest.controller.HeroController;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
class RestApplicationTests {

    @Autowired
    private HeroController heroController;

    @Mock
    private HeroRepository heroRepository;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private MoviesOfTheHeroRepository moviesOfTheHeroRepository;

    @InjectMocks
    private HeroProcessCore heroProcessCore;

    @InjectMocks
    private HeroFightProcessCore heroFightProcessCore;

    @InjectMocks
    private HeroMovieProcessCore heroMovieProcessCore;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetHeroFromDB() {
        final HeroType heroType = new HeroType(1L,"melee");
        final HeroAttribute heroAttribute = new HeroAttribute(1L,38,100,17,80,64,24);
        final Hero hero = new Hero(1L,"A-Bomb","unknown","male","good",heroAttribute,heroType);

        when(heroRepository.findById(1L))
                .thenReturn(Optional.of(hero));



        HeroRequest heroRequest = new HeroRequest(1L);
        HeroResponse heroResponse = HeroResponse.builder()
                .heroId(1L)
                .heroName("A-Bomb")
                .heroAge("unknown")
                .heroGender("male")
                .alignment("good")
                .intelligence("38")
                .power("24")
                .strength("100")
                .speed("17")
                .combat("64")
                .durability("80")
                .heroType("melee")
                .build();

        ResponseEntity<HeroResponse> response = ResponseEntity.ok(heroResponse);

        Assertions.assertNotNull(heroProcessCore.process(heroRequest).get());
        Assertions.assertEquals(response, heroController.getHeroFromDB(heroRequest));



    }
    @Test
    void testGetFightResult(){
        final HeroType heroType = new HeroType(1L,"melee");
        final HeroAttribute heroAttribute = new HeroAttribute(1L,38,100,17,80,64,24);
        final Hero hero = new Hero(1L,"A-Bomb","unknown","male","good",heroAttribute,heroType);

        when(heroRepository.findById(1L))
                .thenReturn(Optional.of(hero));

        final HeroAttribute heroAttribute2 = new HeroAttribute(687L,75,57,65,84,84,86);
        final Hero hero2 = new Hero(687L,"Venom","unknown","male","bad",heroAttribute2,heroType);

        when(heroRepository.findById(687L))
                .thenReturn(Optional.of(hero2));


        HeroFightRequest heroFightRequest = new HeroFightRequest(1L,687L);
        HeroFightResponse heroFightResponse = HeroFightResponse.builder()
                .winnerName("Venom")
                .pointDifference("128")
                .build();

        ResponseEntity<HeroFightResponse> response = ResponseEntity.ok(heroFightResponse);

        Assertions.assertNotNull(heroFightProcessCore.process(heroFightRequest).get());
        Assertions.assertEquals(response,heroController.getFightResult(heroFightRequest));


    }

    @Test
    void testGetHeroMovies(){

        HeroMovieRequest heroMovieRequest = HeroMovieRequest.builder()
                .heroId(1L)
                .build();

        HeroMovieResponse heroMovieResponse = HeroMovieResponse.builder()
                .movieName("Avengers: Infinity War")
                .releaseDate("2018-04-23")
                .build();
        HeroMovieResponse heroMovieResponse2 = HeroMovieResponse.builder()
                .movieName("Avengers: Endgame")
                .releaseDate("2019-04-26")
                .build();

        List<HeroMovieResponse> heroMovieResponses = new ArrayList<>();
        heroMovieResponses.add(heroMovieResponse);
        heroMovieResponses.add(heroMovieResponse2);



        ResponseEntity<List<HeroMovieResponse>> response = ResponseEntity.status(HttpStatus.OK)
                .body(heroMovieResponses);

        Assertions.assertEquals(response,heroController.getHeroMovies(heroMovieRequest));
        HeroMovieRequest fakeRequest = HeroMovieRequest.builder()
                .heroId(5L)
                .build();

        Assertions.assertEquals(HttpStatus.NOT_FOUND, heroController.getHeroMovies(fakeRequest).getStatusCode());

    }

    @Test
    void testGetHeroStats()
    {
        final HeroType heroType = new HeroType(1L,"melee");
        final HeroAttribute heroAttribute = new HeroAttribute(1L,38,100,17,80,64,24);
        final Hero hero = new Hero(1L,"A-Bomb","unknown","male","good",heroAttribute,heroType);

        when(heroRepository.findById(1L))
                .thenReturn(Optional.of(hero));



        HeroRequest heroRequest = new HeroRequest(1L);
        HeroResponse heroResponse = HeroResponse.builder()
                .heroId(1L)
                .heroName("A-Bomb")
                .heroAge("unknown")
                .heroGender("male")
                .alignment("good")
                .intelligence("38")
                .power("24")
                .strength("100")
                .speed("17")
                .combat("64")
                .durability("80")
                .heroType("melee")
                .build();

        ResponseEntity<HeroResponse> response = ResponseEntity.ok(heroResponse);

        Assertions.assertNotNull(heroProcessCore.process(heroRequest).get());
        Assertions.assertEquals(response, heroController.getHeroStats(heroRequest));
    }
}
