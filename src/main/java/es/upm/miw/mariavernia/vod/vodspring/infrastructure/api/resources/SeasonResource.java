package es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.resources;

import es.upm.miw.mariavernia.vod.vodspring.domain.services.SeasonService;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.SeasonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping(SeasonResource.SEASON)
public class SeasonResource {
    public static final String SEASON = "/season";

    private SeasonService seasonService;

    @Autowired
    public SeasonResource(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    @PostMapping(produces = {"application/json"})
    Mono<SeasonDto> create(@Valid @RequestBody SeasonDto seasonDto){
        return this.seasonService.create(seasonDto)
                .map(SeasonDto::new);
    }

}
