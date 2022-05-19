package es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.persistence;

import es.upm.miw.mariavernia.vod.vodspring.domain.model.User;
import es.upm.miw.mariavernia.vod.vodspring.domain.persistence.UserPersistence;
import es.upm.miw.mariavernia.vod.vodspring.domain.exceptions.ConflictException;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.daos.UserReactive;
import es.upm.miw.mariavernia.vod.vodspring.infrastructure.mongodb.entities.UserEntity;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class UserPersistenceMongodb implements UserPersistence {

    private final UserReactive userReactive;

    public UserPersistenceMongodb(UserReactive userReactive) {
        this.userReactive = userReactive;
    }

    @Override
    public Mono<User> create(User user) {
        UserEntity userEntity = new UserEntity(user);
        return this.assertUserNotExist(user.getEmail())
                .then(this.userReactive.save(userEntity)
                        .map(UserEntity::toUser));
    }

    private Mono<Void> assertUserNotExist(String email) {
        return this.userReactive.findByEmail(email)
                .flatMap(userEntity -> Mono.error(
                        new ConflictException("Already exist user with email: " + email)
                        ));
    }

}
