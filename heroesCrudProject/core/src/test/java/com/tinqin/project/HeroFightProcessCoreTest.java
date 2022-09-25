package com.tinqin.project;

import com.tinqin.project.data.entity.Hero;
import com.tinqin.project.data.entity.HeroAttribute;
import com.tinqin.project.data.entity.HeroType;
import com.tinqin.project.data.repository.HeroRepository;
import com.tinqin.project.model.fight.HeroFightRequest;
import com.tinqin.project.model.fight.HeroFightResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.Mockito.when;

public class HeroFightProcessCoreTest {
    @Mock
    private HeroRepository heroRepository;

    @InjectMocks
    private HeroFightProcessCore heroFightProcessCore;

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

        final HeroAttribute heroAttribute2 = new HeroAttribute(687L,75,57,65,84,84,86);
        final Hero hero2 = new Hero(687L,"Venom","unknown","male","bad",heroAttribute2,heroType);

        when(heroRepository.findById(687L))
                .thenReturn(Optional.of(hero2));


        HeroFightRequest heroFightRequest = new HeroFightRequest(1L,687L);
        HeroFightResponse heroFightResponse = HeroFightResponse.builder()
                .winnerName("Venom")
                .pointDifference("128")
                .build();


        Assertions.assertNotNull(heroFightProcessCore.process(heroFightRequest).get());
        Assertions.assertEquals(heroFightResponse,heroFightProcessCore.process(heroFightRequest).get());
    }
}
