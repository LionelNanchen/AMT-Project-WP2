package ch.heigvd.gamification.repository;

import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Rule;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Authors: Amrani Kamil, Nanchen Lionel, Nicole Olivier, Reka Mentor
 * AMT WP2 2018-2019
 */
public interface RulesRepository extends CrudRepository<Rule, Integer> {
    List<Rule> findAllByBadge_Application(Application application);
    Rule findByBadge_ApplicationAndId(Application application, int id);
    List<Rule> findAllByTypeAndBadge_Application(String type, Application application);
}