package es.upm.miw.mariavernia.vod.vodspring.domain.services;

import es.upm.miw.mariavernia.vod.vodspring.TestConfig;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.SeasonDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;
import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class SeasonServiceIT {

    @Autowired
    SeasonService seasonService;

    @Test
    void testCreate() {
        SeasonDto seasonDto = SeasonDto.builder()
                .reference("SUB2016")
                .name("2016")
                .subjectReference("613000096")
                .build();
        StepVerifier
                .create(this.seasonService.create(seasonDto))
                .expectNextMatches(season -> {
                    assertEquals(seasonDto.getName(), season.getName());
                    return true;
                })
                .verifyComplete();
    }

    @Test
    void testFindAllReferences() {
        StepVerifier
                .create(this.seasonService.findAllReferences())
                .expectComplete();
    }
}
