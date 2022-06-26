package es.upm.miw.mariavernia.vod.vodspring.domain.services;

import es.upm.miw.mariavernia.vod.vodspring.TestConfig;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.SubjectDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class SubjectServiceIT {

    @Autowired
    SubjectService subjectService;

    @Test
    void testCreate() {
        List<String> authors = Arrays.asList("Author 1", "Author 2");

        SubjectDto subjectDto = SubjectDto.builder()
                .reference("RefTest1")
                .name("Test1")
                .description("test description")
                .authors(authors)
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
