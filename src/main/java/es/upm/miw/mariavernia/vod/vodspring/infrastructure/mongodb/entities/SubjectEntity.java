package es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.entities;

import es.upm.miw.mariavernia.vod.vodspring.domain.model.User;
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
public class SubjectEntity {
    @Id
    private int id;
    private String name;
    private String description;
    private List<SeasonEntity> seasons;
    private List<User> authors;
}
