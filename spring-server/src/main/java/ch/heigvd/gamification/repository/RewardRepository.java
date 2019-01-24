package ch.heigvd.gamification.repository;

import ch.heigvd.gamification.model.Reward;
import ch.heigvd.gamification.model.Rule;
import ch.heigvd.gamification.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RewardRepository extends CrudRepository<Reward, Long> {
    Reward findByUserAndRule(User user, Rule rule);
}
