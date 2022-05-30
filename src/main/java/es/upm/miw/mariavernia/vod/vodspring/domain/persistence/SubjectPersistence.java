package es.upm.miw.mariavernia.vod.vodspring.domain.persistence;

import es.upm.miw.mariavernia.vod.vodspring.domain.model.Subject;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface SubjectPersistence {
    Flux<Subject> readAll();

    Mono<Subject> create(Subject subject);

    Mono<Subject> readByReference(String reference);

    Flux<List<String>> findAllReferences();
}
