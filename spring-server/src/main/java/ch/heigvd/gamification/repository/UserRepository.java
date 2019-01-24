package ch.heigvd.gamification.repository;

import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByRemoteUserIdAndApplication(String uId, Application application);
}
