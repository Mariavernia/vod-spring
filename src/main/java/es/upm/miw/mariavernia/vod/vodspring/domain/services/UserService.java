package es.upm.miw.mariavernia.vod.vodspring.domain.services;

import es.upm.miw.mariavernia.vod.vodspring.domain.model.User;
import es.upm.miw.mariavernia.vod.vodspring.domain.persistence.UserPersistence;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.api.dtos.UserDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    private final UserPersistence userPersistence;

    public UserService(UserPersistence userPersistence) {
        this.userPersistence = userPersistence;
    }

    public Mono<User> create(UserDto userDto) {
        User user = userDto.toUser();
        return this.userPersistence.create(user);
    }
}
