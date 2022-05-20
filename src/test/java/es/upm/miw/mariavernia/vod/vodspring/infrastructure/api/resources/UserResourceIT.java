package es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.resources;

import es.upm.miw.mariavernia.vod.vodspring.domain.model.Role;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.RestClientTestService;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
public class UserResourceIT {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private RestClientTestService restClientTestService;

    public static final String USERS = "/users";

    @Test
    void testCreate() {
        UserDto userDto =  UserDto.builder()
                .firstName("Maria")
                .familyName("Garcia")
                .email("mariagarcia2_email@upm.es")
                .password("contra123")
                .role(Role.PROFESSOR)
                .active(true)
                .build();

        this.webTestClient
                .post()
                .uri(USERS)
                .body(BodyInserters.fromValue(userDto))
                .exchange()
                .expectStatus()
                //.isForbidden();
                .isOk()
                .expectBody(UserDto.class)
                .value(Assertions::assertNotNull);
    }
}
