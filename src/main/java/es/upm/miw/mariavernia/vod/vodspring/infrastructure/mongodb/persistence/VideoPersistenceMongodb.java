package es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.persistence;

import es.upm.miw.mariavernia.vod.vodspring.domain.exceptions.ConflictException;
import es.upm.miw.mariavernia.vod.vodspring.domain.model.Video;
import es.upm.miw.mariavernia.vod.vodspring.domain.persistence.VideoPersistence;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.VideoDto;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.daos.SeasonReactive;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.daos.VideoReactive;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.entities.SeasonEntity;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.entities.VideoEntity;
import org.springframework.stereotype.Repository;
import org.webjars.NotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class VideoPersistenceMongodb implements VideoPersistence {

    private final VideoReactive videoReactive;
    public VideoPersistenceMongodb(VideoReactive videoReactive) {
        this.videoReactive = videoReactive;
    }

    @Override
    public Mono<Video> create(Video video) {
        VideoEntity videoEntity = new VideoEntity(video);
        return this.assertVideoNotExist(video.getLink())
                .then(this.videoReactive.save(videoEntity)
                        .map(VideoEntity::toVideo));
    }

    @Override
    public Flux<Video> findVideosBySeasonReference(String reference) {
        return this.videoReactive.findBySeasonReference(reference)
                .switchIfEmpty(
                        Flux.error(new NotFoundException("No se ha encontrado ninguna temporada con la referencia: " + reference))
                )
                .map(VideoEntity::toVideo);
    }


    private Mono<Void> assertVideoNotExist(String linkVideo) {
        return this.videoReactive.findByLink(linkVideo)
                .flatMap( seasonEntity -> Mono.error(
                        new ConflictException("Video already exist: " + linkVideo)
                ));
    }
}
