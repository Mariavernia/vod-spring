package es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.resources;

import es.upm.miw.mariavernia.vod.vodspring.domain.model.Role;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
public class UserResourceIT {

    public static final String USER = "/user";
    public static final String TOKEN = "/token";

    @Autowired
    private WebTestClient webTestClient;

}
