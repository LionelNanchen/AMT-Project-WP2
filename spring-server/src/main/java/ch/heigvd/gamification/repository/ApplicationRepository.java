package ch.heigvd.gamification.repository;

import ch.heigvd.gamification.model.Application;
import org.springframework.data.repository.CrudRepository;

/**
 * Authors: Amrani Kamil, Nanchen Lionel, Nicole Olivier, Reka Mentor
 * AMT WP2 2018-2019
 */
public interface ApplicationRepository extends CrudRepository<Application, Long> {
    Application findByAppKey(String appKey);
}
