package es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.resources;

import es.upm.miw.mariavernia.vod.vodspring.domain.services.UserService;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.TokenDto;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.UserDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.validation.Valid;

//@PreAuthorize("hasRole('ADMIN') or hasRole('PROFESSOR') or hasRole('STUDENT')")
@RestController
@RequestMapping(UserResource.USER)
public class UserResource {
    public static final String USER = "/user";
    public static final String TOKEN = "/token";
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    //@SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public Mono<UserDto> create(@Valid @RequestBody UserDto userDto) {
        return this.userService.create(userDto)
                .map(UserDto::new);
    }

    //@SecurityRequirement(name = "basicAuth")
    //@PreAuthorize("authenticated")
    @PostMapping(TOKEN)
    public Mono<TokenDto> login(@Valid @RequestBody UserDto activeUser) {
        System.out.println("LOGIIIN" );
        System.out.println("LOGIIIN user resource: " + activeUser.getEmail());
        System.out.println("LOGIIIN 2" );
        return userService.login(activeUser.getEmail())
                .map(TokenDto::new);
    }


}
