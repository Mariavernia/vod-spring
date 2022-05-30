package es.upm.miw.mariavernia.vod.vodspring.domain.services;

import es.upm.miw.mariavernia.vod.vodspring.domain.model.Video;
import es.upm.miw.mariavernia.vod.vodspring.domain.persistence.SeasonPersistence;
import es.upm.miw.mariavernia.vod.vodspring.domain.persistence.VideoPersistence;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.VideoDto;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import reactor.core.publisher.Mono;

@Service
public class VideoService {

    private final VideoPersistence videoPersistence;
    private final SeasonPersistence seasonPersistence;

    public VideoService(VideoPersistence videoPersistence, SeasonPersistence seasonPersistence) {
        this.videoPersistence = videoPersistence;
        this.seasonPersistence = seasonPersistence;
    }

    public Mono<Video> create(VideoDto videoDto) {
        Video video = new Video();
        video.setName(videoDto.getName());
        video.setDescription(videoDto.getDescription());
        video.setLink(videoDto.getLink());
        Mono<Void> seasonMono = this.setSeason(video, videoDto.getSeasonReference()).then();
        return Mono.when(seasonMono)
                .then(this.videoPersistence.create(video));
    }

    private Mono<Video> setSeason(Video video, String seasonReference) {
        return this.seasonPersistence.readByReference(seasonReference)
                .switchIfEmpty(Mono.error(
                        new NotFoundException("Season not found: " + seasonReference)
                ))
                .map(season -> {
                    video.setSeason(season);
                    return video;
                });
    }

}
