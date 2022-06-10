package es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.resources;

import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.SeasonDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
public class SeasonResourceIT {

    public static final String SEASON = "/season";
    public static final String REFERENCE = "/reference";
    public static final String VIDEO = "/video";

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreateIsOK() {
        SeasonDto seasonDto = SeasonDto.builder()
                .reference("SUB2016")
                .name("2016")
                .subjectReference("613000096")
                .build();
        this.webTestClient
                .post()
                .uri(SEASON)
                .body(BodyInserters.fromValue(seasonDto))
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void testFindAllReferences() {
        this.webTestClient
                .get()
                .uri(SEASON + REFERENCE)
                .exchange()
                .expectStatus()
                .isOk();
    }
    @Test
    void testFindVideosBySeasonReference() {
        String reference = " 1516IWVG";
        this.webTestClient
                .get()
                .uri(SEASON + '/' + reference  + VIDEO)
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }

}
