package es.upm.miw.mariavernia.vod.vodspring.domain.services;

import es.upm.miw.mariavernia.vod.vodspring.domain.model.Season;
import es.upm.miw.mariavernia.vod.vodspring.domain.persistence.SeasonPersistence;
import es.upm.miw.mariavernia.vod.vodspring.domain.persistence.SubjectPersistence;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.SeasonDto;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class SeasonService {

    private final SubjectPersistence subjectPersistence;
    private final SeasonPersistence seasonPersistence;

    public SeasonService(SubjectPersistence subjectPersistence, SeasonPersistence seasonPersistence) {
        this.subjectPersistence = subjectPersistence;
        this.seasonPersistence = seasonPersistence;
    }

    public Mono<Season> create(SeasonDto seasonDto) {
        Season season = new Season();
        season.setName(seasonDto.getName());
        season.setReference(seasonDto.getReference());
        Mono<Void> subjectMono = this.setSubject(season, seasonDto.getSubjectReference()).then();
        return Mono.when(subjectMono)
                .then(this.seasonPersistence.create(season));
    }

    private Mono<Season> setSubject(Season season , String reference) {
        return this.subjectPersistence.readByReference(reference)
                .switchIfEmpty(Mono.error(
                        new NotFoundException("Subject not found:" + reference)
                ))
                .map(subject -> {
                    season.setSubject(subject);
                    return season;
                });
    }

    public Flux<List<String>> findAllReferences() {
        return this.seasonPersistence.findAllReferences()
                .distinct();
    }
}
