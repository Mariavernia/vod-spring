package es.upm.miw.mariavernia.vod.vodspring.domain.persistence;

import es.upm.miw.mariavernia.vod.vodspring.domain.model.Video;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface VideoPersistence {

    Mono<Video> create(Video video);

}
