package es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.daos;

import es.upm.miw.mariavernia.vod.vodspring.domain.model.Season;
import es.upm.miw.mariavernia.vod.vodspring.domain.model.Subject;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.SeasonDto;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.entities.SeasonEntity;
import org.reactivestreams.Publisher;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface SeasonReactive extends ReactiveSortingRepository<SeasonEntity, String> {
    Mono<SeasonEntity> findByReference(String reference);

    Flux<SeasonEntity> findBySubjectReference(String subjectReference);
}
