package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.BadgesDTO;
import ch.heigvd.gamification.api.dto.BadgesResponseDTO;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.Badge;
import ch.heigvd.gamification.repository.ApplicationRepository;
import ch.heigvd.gamification.repository.BadgeRepository;
import ch.heigvd.gamification.util.ModelToDTOConverter;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;
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
    public ResponseEntity<List<BadgesResponseDTO>> badgesGet(@ApiParam(value = "token that contains the application key" ,required=true) @RequestHeader(value="X-Api-Key", required=true) String xApiKey) {
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
    public ResponseEntity<Void> badgesPost(@ApiParam(value = "token that contains the application key" ,required=true) @RequestHeader(value="X-Api-Key", required=true) String xApiKey,@ApiParam(value = "The new badge need to have a name and a description." ,required=true )  @Valid @RequestBody ch.heigvd.gamification.api.dto.BadgesDTO body) {
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

    @Override
    public ResponseEntity<Void> badgesIdDelete(@ApiParam(value = "token that contains the application key" ,required=true) @RequestHeader(value="X-Api-Key", required=true) String xApiKey,@ApiParam(value = "Badge id to delete",required=true) @PathVariable("id") Long id) {
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
    public ResponseEntity<Void> badgesIdPut(@ApiParam(value = "token that contains the application key" ,required=true) @RequestHeader(value="X-Api-Key", required=true) String xApiKey,@ApiParam(value = "Badge id to update",required=true ) @PathVariable("id") Long id,@ApiParam(value = "The badge must have a new name and a new description" ,required=true )  @Valid @RequestBody BadgesDTO body) {
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
