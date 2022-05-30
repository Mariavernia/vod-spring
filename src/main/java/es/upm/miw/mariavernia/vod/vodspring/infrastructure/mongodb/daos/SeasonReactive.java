package es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.daos;

import es.upm.miw.mariavernia.vod.vodspring.domain.model.Season;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.entities.SeasonEntity;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Mono;

public interface SeasonReactive extends ReactiveSortingRepository<SeasonEntity, String> {
    Mono<Season> findByReference(String reference);
}
