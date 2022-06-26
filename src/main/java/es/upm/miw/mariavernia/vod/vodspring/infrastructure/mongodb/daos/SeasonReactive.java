package es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.daos;

import es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.entities.SeasonEntity;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SeasonReactive extends ReactiveSortingRepository<SeasonEntity, String> {
    Mono<SeasonEntity> findByReference(String reference);

    Flux<SeasonEntity> findBySubjectReference(String subjectReference);
}
