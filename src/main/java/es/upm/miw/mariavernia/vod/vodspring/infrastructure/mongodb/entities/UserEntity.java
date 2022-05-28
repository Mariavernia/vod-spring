package es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.entities;

import es.upm.miw.mariavernia.vod.vodspring.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class UserEntity {
    @Id
    private int id;
    private String reference;
    private String firstName;
    private String familyName;
    private String email;
    private String password;
    private String role;
    private Boolean active;

    public UserEntity(User user) {
        BeanUtils.copyProperties(user, this);
        if(Objects.nonNull(user.getRole())){
            this.role = user.getRole().toString();
        }
    }

    public User toUser() {
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }
}
