package es.upm.miw.mariavernia.vod.vodspring.configuration;

import es.upm.miw.mariavernia.vod.vodspring.TestConfig;
import es.upm.miw.mariavernia.vod.vodspring.domain.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class JwtServiceTest {
    @Autowired
    private JwtService jwtService;

    @Test
    void testCreateToken() {
        String token = jwtService.createToken("666666000", "adm", Role.ADMIN.name());
        assertFalse(token.isEmpty());
        assertEquals(3, token.split("\\.").length);
    }
}
