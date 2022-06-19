package es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import es.upm.miw.mariavernia.vod.vodspring.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthRequest {
    @NotNull
    @NotBlank
    private String email;
    private String password;

}
