package ch.heigvd.gamification.repository;

import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.PointScale;
import org.springframework.data.repository.CrudRepository;

public interface PointScaleRepository extends CrudRepository<PointScale, Long> {
    PointScale findByApplicationAndId(Application app, Long id);
}