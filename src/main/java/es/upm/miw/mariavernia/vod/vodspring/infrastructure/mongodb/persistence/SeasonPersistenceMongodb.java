package es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.persistence;

import es.upm.miw.mariavernia.vod.vodspring.domain.exceptions.ConflictException;
import es.upm.miw.mariavernia.vod.vodspring.domain.model.Season;
import es.upm.miw.mariavernia.vod.vodspring.domain.model.Subject;
import es.upm.miw.mariavernia.vod.vodspring.domain.persistence.SeasonPersistence;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.SeasonDto;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.daos.SeasonReactive;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.daos.SubjectReactive;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.entities.SeasonEntity;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.entities.SubjectEntity;
import org.springframework.stereotype.Repository;
import org.webjars.NotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SeasonPersistenceMongodb implements SeasonPersistence {

    private final SeasonReactive seasonReactive;

    public SeasonPersistenceMongodb(SeasonReactive seasonReactive) {
        this.seasonReactive = seasonReactive;
    }

    @Override
    public Mono<Season> create(Season season) {
        SeasonEntity seasonEntity = new SeasonEntity(season);

        return this.assertSeasonNotExist(season.getReference())
                .then(this.seasonReactive.save(seasonEntity)
                        .map(SeasonEntity::toSeason));
    }

    @Override
    public Mono<Season> readByReference(String seasonReference) {
        return this.seasonReactive.findByReference(seasonReference)
                .map(SeasonEntity::toSeason);
    }

    @Override
    public Flux<List<String>> findAllReferences() {
        List<String> references = new ArrayList<>();
        return this.seasonReactive.findAll()
                .map(seasonEntity -> {
                    references.add(seasonEntity.getReference());
                    return references;
                });
    }

    @Override
    public Flux<SeasonDto> findBySubjectReference(String subjectReference) {
        return this.seasonReactive.findBySubjectReference(subjectReference)
                .switchIfEmpty(
                        Flux.error(new NotFoundException("No se ha encontrado ninguna asignatura con la referencia: " + subjectReference))
                )
                .map(SeasonEntity::toSeason)
                .map(SeasonDto::new);
    }

    private Mono<Void> assertSeasonNotExist(String reference) {
        return this.seasonReactive.findByReference(reference)
                .flatMap( seasonEntity -> Mono.error(
                        new ConflictException("Season already exist: " + reference)
                ));
    }
}
