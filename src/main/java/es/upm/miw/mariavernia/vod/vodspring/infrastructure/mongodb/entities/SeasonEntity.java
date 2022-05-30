package es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.entities;

import es.upm.miw.mariavernia.vod.vodspring.domain.model.Season;
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
public class SeasonEntity {
    @Id
    private String id;
    private String reference;
    private String name;
    @DBRef
    private SubjectEntity subjects;

    public SeasonEntity(Season season){
        BeanUtils.copyProperties(season,this);
    }

    public Season toSeason(){
        Season season = new Season();
        BeanUtils.copyProperties(this, season);
        return season;
    }
}
