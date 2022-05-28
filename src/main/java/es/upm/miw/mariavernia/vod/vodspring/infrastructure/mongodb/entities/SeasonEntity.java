package es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class SeasonEntity {
    @Id
    private int id;
    private String name;
    private SubjectEntity subjects;
}
