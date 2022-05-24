package es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class AuthorEntity {
    @Id
    private int id;
    private String firstName;
    private String familyName;
    private String email;
}
