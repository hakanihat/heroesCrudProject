package com.tinqin.project;

import com.tinqin.project.data.entity.Hero;
import com.tinqin.project.data.entity.HeroAttribute;
import com.tinqin.project.data.entity.HeroType;
import com.tinqin.project.data.repository.HeroRepository;
import com.tinqin.project.model.appearance.HeroRequest;
import com.tinqin.project.model.appearance.HeroResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.Mockito.when;

public class HeroProcessCoreTest {

    @Mock
    private HeroRepository heroRepository;

    @InjectMocks
    private HeroProcessCore heroProcessCore;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcess(){
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


        Assertions.assertNotNull(heroProcessCore.process(heroRequest).get());
        Assertions.assertEquals(heroResponse, heroProcessCore.process(heroRequest).get());



    }

    @Test
    void  testProcessErrors(){
        when(heroRepository.findAll()).thenReturn(null);

        HeroRequest heroRequest = new HeroRequest();

        Assertions.assertEquals(HttpStatus.NOT_FOUND,heroProcessCore.process(heroRequest).getLeft().getCode());
        Assertions.assertEquals("No such hero exists!",heroProcessCore.process(heroRequest).getLeft().getMessage());
    }
}
