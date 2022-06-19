package es.upm.miw.mariavernia.vod.vodspring.domain.persistence;

import es.upm.miw.mariavernia.vod.vodspring.domain.model.User;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserPersistence {

    Mono<User> create(User user);

    Mono<String> login(String email);
}
