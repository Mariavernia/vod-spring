package es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.resources;

import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.VideoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
public class VideoResourceIT {

    public static final String VIDEO = "/video";

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreateIsServerError(){
        VideoDto videoDto = VideoDto.builder()
                .name("Nombre del video")
                .description("descripcion del video")
                .link("Link del video")
                .seasonReference("VTFY5")
                .build();
        this.webTestClient
                .post()
                .uri(VIDEO)
                .body(BodyInserters.fromValue(videoDto))
                .exchange()
                .expectStatus()
                .isOk();
    }


}
