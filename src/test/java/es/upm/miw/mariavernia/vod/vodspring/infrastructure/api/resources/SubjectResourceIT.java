package es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.resources;

import es.upm.miw.mariavernia.vod.vodspring.domain.model.Subject;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.SubjectDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.io.Console;
import java.util.Arrays;
import java.util.List;

@RestTestConfig
public class SubjectResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    public static final String SUBJECT = "/subject";
    public static final String SEARCH = "/search";

    @Test
    void testCreate() {
        SubjectDto subject = SubjectDto.builder()
                .reference("sub1")
                .name("Subject 1")
                .description("Description subject 1")
                .authors("Author 1")
                .build();

        this.webTestClient
                .post()
                .uri(SUBJECT)
                .body(BodyInserters.fromValue(subject))
                .exchange()
                .expectStatus()
                .isOk();
        System.out.println("URI: " + this.webTestClient.post().uri(SUBJECT).toString());

    }

    @Test
    void testFIndAllSubjects() {
        this.webTestClient
                .get()
                .uri(SUBJECT+SEARCH)
                .exchange()
                .expectStatus()
                .isOk();
    }

}
