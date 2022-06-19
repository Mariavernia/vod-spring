package es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.persistence;

import es.upm.miw.mariavernia.vod.vodspring.TestConfig;
import es.upm.miw.mariavernia.vod.vodspring.domain.model.Role;
import es.upm.miw.mariavernia.vod.vodspring.domain.model.User;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.AuthRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;
import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class UserPersistenceMondodbIT {
    @Autowired
    private UserPersistenceMongodb userPersistenceMongodb;

    @Test
    void testCrete() {
        User user = User.builder()
                .firstName("Maria")
                .familyName("Garcia")
                .email("mariagarcia_email@upm.es")
                .password("contra123")
                .role(Role.PROFESSOR)
                .active(true)
                .build();
        StepVerifier
                .create(this.userPersistenceMongodb.create(user))
                .expectNextMatches(dbUser -> {
                    assertEquals("Maria", dbUser.getFirstName());
                    return true;
                })
                .verifyComplete();
    }
/*
    @Test
    void testLogin() {
        AuthRequest user = AuthRequest.builder()
                .email("test1@test1.com")
                .password("maria1234")
                .build();
        StepVerifier
                .create(this.userPersistenceMongodb.login(user.getEmail()))
                .expectNextMatches(token -> {
                    assertNotNull(token);
                    return true;
                })
                .verifyComplete();
    }*/
}
