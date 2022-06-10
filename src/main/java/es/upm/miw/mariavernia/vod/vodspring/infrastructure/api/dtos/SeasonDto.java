package es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import es.upm.miw.mariavernia.vod.vodspring.domain.model.Season;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeasonDto {
    private String reference;
    private String name;
    private String subjectReference;

    public SeasonDto(Season season){
        BeanUtils.copyProperties(season, this);
    }
}
