package es.upm.miw.mariavernia.vod.vodspring.domain.services;

import es.upm.miw.mariavernia.vod.vodspring.domain.exceptions.ConflictException;
import es.upm.miw.mariavernia.vod.vodspring.domain.model.Season;
import es.upm.miw.mariavernia.vod.vodspring.domain.model.Subject;
import es.upm.miw.mariavernia.vod.vodspring.domain.persistence.SeasonPersistence;
import es.upm.miw.mariavernia.vod.vodspring.domain.persistence.SubjectPersistence;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.SeasonDto;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.SubjectDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

@Service
public class SubjectService {

    private final SubjectPersistence subjectPersistence;
    private final SeasonPersistence seasonPersistence;

    public SubjectService(SubjectPersistence subjectPersistence, SeasonPersistence seasonPersistence) {
        this.subjectPersistence = subjectPersistence;
        this.seasonPersistence = seasonPersistence;
    }

    public Flux<Subject> findAllSubject() {
        return this.subjectPersistence.readAll();
    }

    public Mono<Subject> create(SubjectDto subjectDto) {
        Subject subject = new Subject();
        subject.setName(subjectDto.getName());
        subject.setReference(subjectDto.getReference());
        subject.setDescription(subjectDto.getDescription());
        subject.setAuthors(subjectDto.getAuthors());
        return this.subjectPersistence.create(subject);
    }

    public Flux<List<String>> findAllReferences() {
        return this.subjectPersistence.findAllReferences()
                .distinct();
    }

    public Flux<SeasonDto> findAllSeasonsBySubject(String reference) {
        return this.seasonPersistence.findBySubjectReference(reference)
                .distinct();
    }

}
