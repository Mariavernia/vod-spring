package es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos;


import com.fasterxml.jackson.annotation.JsonInclude;
import es.upm.miw.mariavernia.vod.vodspring.domain.model.Video;
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
public class VideoDto {
    private String name;
    private String description;
    private String link;
    private String seasonReference;

    public VideoDto(Video video){
        BeanUtils.copyProperties(video, this);
        this.seasonReference = video.getSeason().getReference();
    }
}
