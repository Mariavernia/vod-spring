package es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.resources;

import es.upm.miw.mariavernia.vod.vodspring.domain.services.VideoService;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.VideoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping(VideoResource.VIDEO)
public class VideoResource {
    public static final String VIDEO = "/video";

    private VideoService videoService;

    @Autowired
    public VideoResource(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping(produces = {"application/json"})
    Mono<VideoDto> create(@Valid @RequestBody VideoDto videoDto){
        return this.videoService.create(videoDto)
                .map(VideoDto::new);
    }

}
