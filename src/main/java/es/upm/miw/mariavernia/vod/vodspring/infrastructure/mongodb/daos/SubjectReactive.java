package es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.daos;

import es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.entities.SubjectEntity;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Mono;

public interface SubjectReactive extends ReactiveSortingRepository<SubjectEntity, String> {

    Mono<Void> findByReference(String reference);
}
