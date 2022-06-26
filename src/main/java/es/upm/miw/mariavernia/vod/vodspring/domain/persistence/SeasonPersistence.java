package es.upm.miw.mariavernia.vod.vodspring.domain.persistence;

import es.upm.miw.mariavernia.vod.vodspring.domain.model.Season;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.SeasonDto;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface SeasonPersistence {
    Mono<Season> create(Season season);

    Mono<Season> readByReference(String seasonReference);

    Flux<List<String>> findAllReferences();

    Flux<SeasonDto> findBySubjectReference(String subjectReference);
}
