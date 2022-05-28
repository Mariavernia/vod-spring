package es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.persistence;

import es.upm.miw.mariavernia.vod.vodspring.TestConfig;
import es.upm.miw.mariavernia.vod.vodspring.domain.model.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class SubjectPersistenceMongodbIT {

    @Autowired
    private SubjectPersistenceMongodb subjectPersistenceMongodb;
/*
    @Test
    void testCreate() {
        Subject subject = Subject.builder()
                .name("Subject 1")
                .reference("sub1")
                .description("Description subject 1")
                .authors("Author 1")
                .build();
        StepVerifier
                .create(this.subjectPersistenceMongodb.create(subject))
                .expectNextMatches(dbSubject -> {
                    assertEquals("Subject 1", dbSubject.getName());
                    return true;
                })
                .verifyComplete();
    }

    @Test
    void testReadAll() {
        Subject subject = Subject.builder()
                .name("Subject 1")
                .reference("sub1")
                .description("Description subject 1")
                .authors("Author 1")
                .build();
        this.subjectPersistenceMongodb.create(subject);
        StepVerifier
                .create(this.subjectPersistenceMongodb.readAll())
                .verifyComplete();

    }*/
}
