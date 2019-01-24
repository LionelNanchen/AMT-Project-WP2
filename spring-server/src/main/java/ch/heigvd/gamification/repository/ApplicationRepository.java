package ch.heigvd.gamification.repository;

import ch.heigvd.gamification.model.Application;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationRepository extends CrudRepository<Application, Long> {
    Application findByAppKey(String appKey);
}
