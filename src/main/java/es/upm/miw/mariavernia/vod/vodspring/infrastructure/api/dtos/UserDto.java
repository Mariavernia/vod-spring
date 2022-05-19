package es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import es.upm.miw.mariavernia.vod.vodspring.domain.model.Role;
import es.upm.miw.mariavernia.vod.vodspring.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    @NotNull
    @NotBlank
    @Pattern(regexp = Validations.NINE_DIGITS)
    private String mobile;
    @NotNull
    @NotBlank
    private String firstName;
    private String familyName;
    private String email;
    private String password;
    private Role role;
    private Boolean active;
    private LocalDateTime registrationDate;

    public UserDto(User user) {
        BeanUtils.copyProperties(user, this);
        this.password = "secret";
    }

    public static UserDto ofMobileFirstName(User user) {
        return UserDto.builder().firstName(user.getFirstName()).build();
    }

    public void doDefault() {
        if (Objects.isNull(role)) {
            this.role = Role.STUDENT;
        }
        if (Objects.isNull(active)) {
            this.active = true;
        }
    }

    public User toUser() {
        this.doDefault();
        User user = new User();
        BeanUtils.copyProperties(this, user);
        user.setPassword(new BCryptPasswordEncoder().encode(this.password));
        return user;
    }
}
