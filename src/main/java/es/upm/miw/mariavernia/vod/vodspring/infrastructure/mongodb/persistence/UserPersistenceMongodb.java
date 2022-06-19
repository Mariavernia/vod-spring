package es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.persistence;

import es.upm.miw.mariavernia.vod.vodspring.configuration.JwtService;
import es.upm.miw.mariavernia.vod.vodspring.domain.exceptions.ConflictException;
import es.upm.miw.mariavernia.vod.vodspring.domain.model.User;
import es.upm.miw.mariavernia.vod.vodspring.domain.persistence.UserPersistence;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.daos.UserReactive;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.entities.UserEntity;
import org.springframework.stereotype.Repository;
import org.webjars.NotFoundException;
import reactor.core.publisher.Mono;

@Repository
public class UserPersistenceMongodb implements UserPersistence {

    private final UserReactive userReactive;
    private final JwtService jwtService;

    public UserPersistenceMongodb(UserReactive userReactive, JwtService jwtService) {
        this.userReactive = userReactive;
        this.jwtService = jwtService;
    }

    @Override
    public Mono<User> create(User user) {
        UserEntity userEntity = new UserEntity(user);
        this.assertUserNotExist(user.getEmail());
        return this.userReactive.save(userEntity)
                        .map(UserEntity::toUser);
    }

    @Override
    public Mono<String> login(String email) {
        System.out.println("USER MONGO: " + email);
        return this.userReactive.findByEmail(email)
                .switchIfEmpty(
                        Mono.error(new NotFoundException("User does not exist: " + email))
                )
                .map(userEntity -> jwtService.createToken(userEntity.getEmail(), userEntity.getFirstName(), userEntity.getRole().toString()));
    }


    private Mono<Void> assertUserNotExist(String email) {
        return this.userReactive.findByEmail(email)
                .flatMap(userEntity -> Mono.error(
                        new ConflictException("Already exist user with email: " + email)
                ));
    }
}
