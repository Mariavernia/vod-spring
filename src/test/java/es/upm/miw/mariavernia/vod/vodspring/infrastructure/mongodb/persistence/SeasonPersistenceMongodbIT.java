package es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.persistence;

import es.upm.miw.mariavernia.vod.vodspring.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;
import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class SeasonPersistenceMongodbIT {

    @Autowired
    private SeasonPersistenceMongodb seasonPersistenceMongodb;
    /*@Test
    void testFindSeasonsBySubject() {
        String reference = " 613000095";

        this.seasonPersistenceMongodb.findBySubjectReference(reference);
        StepVerifier
                .create(this.seasonPersistenceMongodb.findBySubjectReference(reference))
                .verifyComplete();

    }*/
}
