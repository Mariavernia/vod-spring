package es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.entities;

import es.upm.miw.mariavernia.vod.vodspring.domain.model.Role;
import es.upm.miw.mariavernia.vod.vodspring.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "vodUser")
public class UserEntity {

    @Id
    private String id;
    private String firstName;
    private String familyName;
    @Indexed(unique = true)
    private String email;
    private String password;
    private Role role;
    private Boolean active;

    public UserEntity(User user) {
        BeanUtils.copyProperties(user, this);
    }

    public User toUser() {
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }

}
