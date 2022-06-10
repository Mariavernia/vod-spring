package es.upm.miw.mariavernia.vod.vodspring.domain.persistence;

import es.upm.miw.mariavernia.vod.vodspring.domain.model.Season;
import es.upm.miw.mariavernia.vod.vodspring.domain.model.Video;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.VideoDto;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface VideoPersistence {

    Mono<Video> create(Video video);

    Flux<Video> findVideosBySeasonReference(String reference);
}
