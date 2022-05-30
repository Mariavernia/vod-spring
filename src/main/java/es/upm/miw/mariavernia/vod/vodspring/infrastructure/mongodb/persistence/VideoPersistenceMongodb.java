package es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.persistence;

import es.upm.miw.mariavernia.vod.vodspring.domain.exceptions.ConflictException;
import es.upm.miw.mariavernia.vod.vodspring.domain.model.Video;
import es.upm.miw.mariavernia.vod.vodspring.domain.persistence.VideoPersistence;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.daos.VideoReactive;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.entities.VideoEntity;
import org.springframework.stereotype.Repository;
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

    private Mono<Void> assertVideoNotExist(String linkVideo) {
        return this.videoReactive.findByLink(linkVideo)
                .flatMap( seasonEntity -> Mono.error(
                        new ConflictException("Video already exist: " + linkVideo)
                ));
    }
}
