package ch.heigvd.gamification.repository;

import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.PointScale;
import org.springframework.data.repository.CrudRepository;

/**
 * Authors: Amrani Kamil, Nanchen Lionel, Nicole Olivier, Reka Mentor
 * AMT WP2 2018-2019
 */
public interface PointScaleRepository extends CrudRepository<PointScale, Long> {
    PointScale findByApplicationAndId(Application app, Long id);
}