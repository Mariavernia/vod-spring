package es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.daos;

import es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.entities.VideoEntity;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VideoReactive extends ReactiveSortingRepository<VideoEntity, String> {

    Mono<VideoEntity> findByLink(String linkVideo);

    Flux<VideoEntity> findBySeasonReference(String reference);
}
