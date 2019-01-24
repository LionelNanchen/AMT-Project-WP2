package ch.heigvd.gamification.repository;

import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Badge;
import ch.heigvd.gamification.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Authors: Amrani Kamil, Nanchen Lionel, Nicole Olivier, Reka Mentor
 * AMT WP2 2018-2019
 */
public interface BadgeRepository extends CrudRepository<Badge, Long> {
    Badge findByApplicationAndId(Application application, Long id);
    boolean existsByUsersContainsAndId(User user, long id);
}
