package es.upm.miw.mariavernia.vod.vodspring.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Subject {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;
    private List<Season> seasons;
    private List<Author> authors;
}
