package es.upm.miw.mariavernia.vod.vodspring.domain.services;

import es.upm.miw.mariavernia.vod.vodspring.domain.model.Video;
import es.upm.miw.mariavernia.vod.vodspring.domain.persistence.VideoPersistence;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.VideoDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class VideoService {

    private final VideoPersistence videoPersistence;

    public VideoService(VideoPersistence videoPersistence) {
        this.videoPersistence = videoPersistence;
    }

    public Mono<Video> create(VideoDto videoDto) {
        Video video = new Video();
        video.setName(videoDto.getName());
        video.setDescription(videoDto.getDescription());
        video.setLink(videoDto.getLink());
        video.setSeasonReference(videoDto.getSeasonReference());
        return this.videoPersistence.create(video);
    }


}
