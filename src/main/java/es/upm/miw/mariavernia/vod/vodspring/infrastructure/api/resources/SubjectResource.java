package es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.resources;

import es.upm.miw.mariavernia.vod.vodspring.domain.services.SubjectService;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.SeasonDto;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.SubjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(SubjectResource.SUBJECT)
public class SubjectResource{
    public static final String SUBJECT = "/subject";
    public static final String SEARCH = "/search";
    public static final String REFERENCE = "/reference";
    public static final String REFERENCE_ID = "/{reference}";
    public static final String SEASON = "/season";

    private final SubjectService subjectService;

    @Autowired
    public SubjectResource(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping(SEARCH)
    public Flux<SubjectDto> findAllSubject() {
        return this.subjectService.findAllSubject()
                .map(SubjectDto::new);
    }


    @PostMapping(produces = {"application/json"})
    Mono<SubjectDto> create(@Valid @RequestBody SubjectDto subjectDto){
        return this.subjectService.create(subjectDto)
                .map(SubjectDto::new);

    }

    @GetMapping(REFERENCE)
    public Flux<List<String>> findAllReferences(){
        return this.subjectService.findAllReferences();
    }

    @GetMapping(REFERENCE_ID + SEASON)
    public Flux<SeasonDto> findAllSeasonsBySubject(@PathVariable String reference) {
        return this.subjectService.findAllSeasonsBySubject(reference);
    }

}
