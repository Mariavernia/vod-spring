package es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.entities;

import es.upm.miw.mariavernia.vod.vodspring.domain.model.Video;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class VideoEntity {
    @Id
    private String id;
    private String reference;
    private String name;
    private String description;
    private String link;
    private String seasonReference;

    public VideoEntity(Video video) {
        BeanUtils.copyProperties(video, this);
    }

    public Video toVideo() {
        Video video = new Video();
        BeanUtils.copyProperties(this, video);
        return video;
    }
}
