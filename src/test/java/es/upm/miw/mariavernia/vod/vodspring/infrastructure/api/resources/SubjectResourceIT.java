package es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.resources;

import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.SubjectDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
public class SubjectResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    public static final String SUBJECT = "/subject";
    public static final String SEARCH = "/search";
    public static final String REFERENCE = "/reference";
    public static final String REFERENCE_ID = "/{reference}";
    public static final String SEASON = "/season";
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

    }

    @Test
    void testFindAllSubjects() {
        this.webTestClient
                .get()
                .uri(SUBJECT+SEARCH)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void testFindAllReferences() {
        this.webTestClient
                .get()
                .uri(SUBJECT+REFERENCE)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void testFindAllSeasonsBySubject() {
        String reference = "613000095";

        this.webTestClient
                .get()
                .uri(SUBJECT + '/' + reference  + SEASON)
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }

}
