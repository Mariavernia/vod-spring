package es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.entities;

import es.upm.miw.mariavernia.vod.vodspring.domain.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.index.Indexed;
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
    private String id;
    @Indexed(unique = true)
    private String reference;
    private String name;
    private String description;
    private List<String> authors;

    public SubjectEntity(Subject subject) {
        BeanUtils.copyProperties(subject, this);
    }

    public Subject toSubject() {
        Subject subject = new Subject();
        BeanUtils.copyProperties(this, subject);
        return subject;
    }
}
