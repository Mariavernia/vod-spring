package es.upm.miw.mariavernia.vod.vodspring.domain.services;

import es.upm.miw.mariavernia.vod.vodspring.domain.exceptions.ConflictException;
import es.upm.miw.mariavernia.vod.vodspring.domain.model.Season;
import es.upm.miw.mariavernia.vod.vodspring.domain.model.Video;
import es.upm.miw.mariavernia.vod.vodspring.domain.persistence.SeasonPersistence;
import es.upm.miw.mariavernia.vod.vodspring.domain.persistence.SubjectPersistence;
import es.upm.miw.mariavernia.vod.vodspring.domain.persistence.VideoPersistence;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.SeasonDto;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.VideoDto;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.entities.VideoEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class SeasonService {

    private final SubjectPersistence subjectPersistence;
    private final SeasonPersistence seasonPersistence;
    private final VideoPersistence videoPersistence;

    public SeasonService(SubjectPersistence subjectPersistence, SeasonPersistence seasonPersistence, VideoPersistence videoPersistence) {
        this.subjectPersistence = subjectPersistence;
        this.seasonPersistence = seasonPersistence;
        this.videoPersistence = videoPersistence;
    }

    public Mono<Season> create(SeasonDto seasonDto) {
        Season season = new Season();
        season.setName(seasonDto.getName());
        season.setReference(seasonDto.getReference());
        season.setSubjectReference(seasonDto.getSubjectReference());
        return this.seasonPersistence.create(season);
    }
/*
    private Mono<Season> setSubject(Season season , String reference) {
        return this.subjectPersistence.readByReference(reference)
                .switchIfEmpty(Mono.error(
                        new NotFoundException("Subject not found:" + reference)
                ))
                .map(subject -> {
                    season.setSubject(subject);
                    return season;
                });
    }*/

    public Flux<List<String>> findAllReferences() {
        return this.seasonPersistence.findAllReferences()
                .distinct();
    }

    public Flux<Video> findVideosBySeasonReference(String reference) {
        return this.videoPersistence.findVideosBySeasonReference(reference)
                .distinct();
    }

    private Mono<Void> assertSeasonNotExist(String reference) {
        return this.seasonPersistence.readByReference(reference)
                .flatMap( seasonEntity -> Mono.error(
                        new ConflictException("Season already exist: " + reference)
                ));
    }
}
