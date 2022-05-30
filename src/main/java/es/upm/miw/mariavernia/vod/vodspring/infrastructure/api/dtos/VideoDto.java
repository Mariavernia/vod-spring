package es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoDto {
    private String name;
    private String description;
    private String link;
    private String seasonReference;
}
