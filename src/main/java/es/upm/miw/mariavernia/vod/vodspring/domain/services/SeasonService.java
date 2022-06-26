package es.upm.miw.mariavernia.vod.vodspring.domain.services;

import es.upm.miw.mariavernia.vod.vodspring.domain.exceptions.ConflictException;
import es.upm.miw.mariavernia.vod.vodspring.domain.model.Season;
import es.upm.miw.mariavernia.vod.vodspring.domain.model.Video;
import es.upm.miw.mariavernia.vod.vodspring.domain.persistence.SeasonPersistence;
import es.upm.miw.mariavernia.vod.vodspring.domain.persistence.VideoPersistence;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.SeasonDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class SeasonService {

    private final SeasonPersistence seasonPersistence;
    private final VideoPersistence videoPersistence;

    public SeasonService(SeasonPersistence seasonPersistence, VideoPersistence videoPersistence) {
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

    public Flux<List<String>> findAllReferences() {
        return this.seasonPersistence.findAllReferences()
                .distinct();
    }

    public Flux<Video> findVideosBySeasonReference(String reference) {
        return this.videoPersistence.findVideosBySeasonReference(reference)
                .distinct();
    }
}
