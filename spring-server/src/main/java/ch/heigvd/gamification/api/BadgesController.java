package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.BadgesDTO;
import ch.heigvd.gamification.api.dto.BadgesResponseDTO;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Badge;
import ch.heigvd.gamification.repository.ApplicationRepository;
import ch.heigvd.gamification.repository.BadgeRepository;
import ch.heigvd.gamification.util.ModelToDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import java.util.ArrayList;
import java.util.List;

/**
 * Authors: Amrani Kamil, Nanchen Lionel, Nicole Olivier, Reka Mentor
 * AMT WP2 2018-2019
 */
@RestController
public class BadgesController implements BadgesApi {

    @Autowired
    private  ApplicationRepository applicationRepository;

    @Autowired
    private BadgeRepository badgeRepository;

    @Override
    public ResponseEntity<List<BadgesResponseDTO>> badgesGet(String xApiKey) {
        Application application = applicationRepository.findByAppKey(xApiKey);

        if (application != null) {
            try {
                List<BadgesResponseDTO> response = new ArrayList<>();

                for(Badge badge : application.getBadges()){
                    response.add(ModelToDTOConverter.convert(badge));
                }
                return ResponseEntity.ok(response);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Override
    public ResponseEntity<Void> badgesIdDelete(String xApiKey, Long id) {
        Badge badge = getBadge(xApiKey, id);
        if (badge == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else {
            if (badge == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            badge.getUsers().clear();
            badgeRepository.delete(badge);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @Override
    public ResponseEntity<Void> badgesIdPut(String xApiKey, Long id, BadgesDTO body) {
        Badge badge = getBadge(xApiKey, id);
        if (badge != null) {
            if (!body.getDescription().isEmpty() && !body.getName().isEmpty()) {
                badge.setDescription(body.getDescription());
                badge.setName(body.getName());
            }
            badgeRepository.save(badge);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<Void> badgesPost(String xApiKey, BadgesDTO body) {
        Application application = applicationRepository.findByAppKey(xApiKey);

        if (application != null) {
            Badge badge = new Badge();

            if (!body.getDescription().isEmpty() || !body.getName().isEmpty()) {
                badge.setDescription(body.getDescription());
                badge.setName(body.getName());
                badge.setApplication(application);
            }

            badgeRepository.save(badge);
            return ResponseEntity.status(HttpStatus.CREATED).header("id", String.valueOf(badge.getId())).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    /**
     * get the badge
     * @param apiKey the api key
     * @param id the badge's id
     * @return the retrieved badge
     */
    private Badge getBadge(String apiKey, Long id) {
        Application application = applicationRepository.findByAppKey(apiKey);
        if (application != null) {
            Badge badge = this.badgeRepository.findByApplicationAndId(application, id);
            return badge;
        }
        return null;
    }
}
