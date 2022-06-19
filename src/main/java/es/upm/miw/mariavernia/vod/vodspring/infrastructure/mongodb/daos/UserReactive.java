package es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.daos;

import es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.entities.UserEntity;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface UserReactive extends ReactiveSortingRepository<UserEntity, String> {
    Mono<UserEntity> findByEmail(String email);

    Mono<UserEntity> findByFirstName(String firstName);
}
