package ch.heigvd.gamification.repository;

import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Authors: Amrani Kamil, Nanchen Lionel, Nicole Olivier, Reka Mentor
 * AMT WP2 2018-2019
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByRemoteUserIdAndApplication(String uId, Application application);
    User findUserById(Long id);
}
