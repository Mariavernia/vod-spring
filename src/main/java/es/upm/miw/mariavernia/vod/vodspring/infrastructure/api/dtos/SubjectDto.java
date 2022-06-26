package es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import es.upm.miw.mariavernia.vod.vodspring.domain.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubjectDto {
    private String reference;
    private String name;
    private String description;
    private List<String> authors;

    public SubjectDto(Subject subject){
        BeanUtils.copyProperties(subject, this);

    }
}
