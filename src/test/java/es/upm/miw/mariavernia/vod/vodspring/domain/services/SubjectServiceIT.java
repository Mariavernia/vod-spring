package es.upm.miw.mariavernia.vod.vodspring.domain.services;

import es.upm.miw.mariavernia.vod.vodspring.TestConfig;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.SubjectDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class SubjectServiceIT {

    @Autowired
    SubjectService subjectService;

    @Test
    void testCreate() {

        SubjectDto subjectDto = SubjectDto.builder()
                .reference("RefTest1")
                .name("Test1")
                .description("test description")
                .authors("author1")
                .build();
        StepVerifier
                .create(this.subjectService.create(subjectDto))
                .expectNextMatches(dbSubject -> {
                    assertNotNull(dbSubject.getName());
                    return true;
                })
                .verifyComplete();
    }

    @Test
    void testFindAllReferences() {
        StepVerifier
                .create(this.subjectService.findAllReferences())
                .expectComplete();
    }
}
