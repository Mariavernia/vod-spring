package es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.resources;

import es.upm.miw.mariavernia.vod.vodspring.domain.model.User;
import es.upm.miw.mariavernia.vod.vodspring.domain.services.UserService;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.TokenDto;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.UserDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Optional;

@PreAuthorize("hasRole('ADMIN') or hasRole('STUDENT') or hasRole('PROFESSOR')")
@RestController
@RequestMapping(UserResource.USERS)
public class UserResource {
    public static final String USERS = "/users";
    public static final String TOKEN = "/token";
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @SecurityRequirement(name = "bearerAuth")
    //@PostAuthorize("authenticated")
    @PostMapping
    public Mono<UserDto> create(@Valid @RequestBody UserDto userDto) {
        return this.userService.create(userDto)
                .map(UserDto::new);
    }

    @SecurityRequirement(name = "basicAuth")
    @PreAuthorize("authenticated")
    @PostMapping(TOKEN)
    public Mono<TokenDto> login(@AuthenticationPrincipal UserDto user) {
        return userService.login(user)
                .map(TokenDto::new);
    }


}
