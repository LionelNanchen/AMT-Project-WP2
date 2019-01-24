package ch.heigvd.gamification.repository;

import ch.heigvd.gamification.model.Event;
import ch.heigvd.gamification.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Authors: Amrani Kamil, Nanchen Lionel, Nicole Olivier, Reka Mentor
 * AMT WP2 2018-2019
 */
public interface EventRepository extends CrudRepository<Event, Long> {
    List<Event> findAllByUser(User user);
    List<Event> findAllByType(String type);

}
