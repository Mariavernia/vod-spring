package es.upm.miw.mariavernia.vod.vodspring.domain.persistence;

import es.upm.miw.mariavernia.vod.vodspring.domain.model.Season;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SeasonPersistence {
    Mono<Season> create(Season season);

    Mono<Season> readByReference(String seasonReference);
}
