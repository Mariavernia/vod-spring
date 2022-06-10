package es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.resources;

import es.upm.miw.mariavernia.vod.vodspring.domain.services.SeasonService;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.SeasonDto;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.VideoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(SeasonResource.SEASON)
public class SeasonResource {
    public static final String SEASON = "/season";
    public static final String REFERENCE= "/reference";
    public static final String REFERENCE_ID = "/{reference}";
    public static final String VIDEO = "/video";

    private final SeasonService seasonService;

    @Autowired
    public SeasonResource(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    @PostMapping(produces = {"application/json"})
    Mono<SeasonDto> create(@Valid @RequestBody SeasonDto seasonDto){
        return this.seasonService.create(seasonDto)
                .map(SeasonDto::new);
    }
    @GetMapping(REFERENCE)
    public Flux<List<String>> findAllReferences() {
        return this.seasonService.findAllReferences();
    }

    @GetMapping(REFERENCE_ID + VIDEO)
    public Flux<VideoDto> findVideosBySeasonReference(@PathVariable String reference){
        return this.seasonService.findVideosBySeasonReference(reference)
                .map(VideoDto::new);
    }

}
