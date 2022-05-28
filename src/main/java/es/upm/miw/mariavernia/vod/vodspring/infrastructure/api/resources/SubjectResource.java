package es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.resources;

import es.upm.miw.mariavernia.vod.vodspring.domain.model.Subject;
import es.upm.miw.mariavernia.vod.vodspring.domain.services.SubjectService;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.Rest;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.SubjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping(SubjectResource.SUBJECT)
public class SubjectResource{
    public static final String SUBJECT = "/subject";
    public static final String SEARCH = "/search";

    private SubjectService subjectService;

    @Autowired
    public SubjectResource(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping(SEARCH)
    public Flux<SubjectDto> findAllSubject() {
        System.out.println("Subject find all in resource:" );
        return this.subjectService.findAllSubject()
                .map(SubjectDto::new);
    }


    @PostMapping(produces = {"application/json"})
    Mono<SubjectDto> create(@Valid @RequestBody SubjectDto subjectDto){
        System.out.println("Subject create in resource:" + subjectDto);
        return this.subjectService.create(subjectDto)
                .map(SubjectDto::new);

    }
}
