package es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.persistence;

import es.upm.miw.mariavernia.vod.vodspring.domain.exceptions.ConflictException;
import es.upm.miw.mariavernia.vod.vodspring.domain.model.Subject;
import es.upm.miw.mariavernia.vod.vodspring.domain.persistence.SubjectPersistence;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.daos.SubjectReactive;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.entities.SubjectEntity;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class SubjectPersistenceMongodb implements SubjectPersistence {

    private final SubjectReactive subjectReactive;

    public SubjectPersistenceMongodb(SubjectReactive subjectReactive) {
        this.subjectReactive = subjectReactive;
    }

    @Override
    public Flux<Subject> readAll() {
        return this.subjectReactive.findAll().map(SubjectEntity::toSubject);
    }

    @Override
    public Mono<Subject> create(Subject subject) {
        SubjectEntity subjectEntity = new SubjectEntity(subject);
        System.out.println("Subject in mongo:" + subjectEntity);
        return this.assertSubjectNotExist(subject.getReference())
                .then(this.subjectReactive.save(subjectEntity)
                        .map(SubjectEntity::toSubject));
    }

    private Mono<Void> assertSubjectNotExist(String reference) {
        return this.subjectReactive.findByReference(reference)
                .flatMap(SubjectEntity -> Mono.error(
                        new ConflictException("Subject already exist")
                ));
    }
}
