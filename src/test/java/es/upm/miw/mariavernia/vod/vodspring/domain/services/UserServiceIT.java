package es.upm.miw.mariavernia.vod.vodspring.domain.services;

import es.upm.miw.mariavernia.vod.vodspring.TestConfig;
import es.upm.miw.mariavernia.vod.vodspring.domain.model.Role;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

    @TestConfig
public class UserServiceIT {

        /*
    @Autowired
    UserService userService;



    @Test
    void testCreate() {
        UserDto userDto = UserDto.builder()
                .firstName("Maria")
                .familyName("Garcia")
                .email("mariagarcia_email@upm.es")
                .password("contra123")
                .role(Role.PROFESSOR)
                .active(true)
                .build();
        StepVerifier
                .create(this.userService.create(userDto))
                .expectNextMatches(dbUser -> {
                    assertEquals("Maria", dbUser.getFirstName());
                    return true;
                })
                .verifyComplete();
    }*/
    }

