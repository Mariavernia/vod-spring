package es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.persistence;

import es.upm.miw.mariavernia.vod.vodspring.domain.exceptions.ConflictException;
import es.upm.miw.mariavernia.vod.vodspring.domain.model.Season;
import es.upm.miw.mariavernia.vod.vodspring.domain.persistence.SeasonPersistence;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.daos.SeasonReactive;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.entities.SeasonEntity;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

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

    private Mono<Void> assertSeasonNotExist(String reference) {
        return this.seasonReactive.findByReference(reference)
                .flatMap( seasonEntity -> Mono.error(
                        new ConflictException("Season already exist: " + reference)
                ));
    }
}
