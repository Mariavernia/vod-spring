package es.upm.miw.mariavernia.vod.vodspring.domain.services;

import es.upm.miw.mariavernia.vod.vodspring.TestConfig;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.VideoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

@TestConfig
public class VideoServiceIT {

    @Autowired
    VideoService videoService;

    @Test
    void testCreate() {
        VideoDto videoDto = VideoDto.builder()
                .name("Nombre del video")
                .description("descripcion del video")
                .link("Link del video")
                .seasonReference("VTFY5")
                .build();

        StepVerifier
                .create(this.videoService.create(videoDto))
                .expectError()
                .verify();
    }
}
