package ch.heigvd.gamification.api;

import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 * Authors: Amrani Kamil, Nanchen Lionel, Nicole Olivier, Reka Mentor
 * AMT WP2 2018-2019
 */
@RestController
public class BadgesController implements BadgesApi {

    ApplicationRepository applicationRepository;
    BadgeRepository badgeRepository;

    public BadgesController(ApplicationRepository applicationRepository, BadgeRepository badgeRepository) {
        this.applicationRepository = applicationRepository;
        this.badgeRepository = badgeRepository;
    }

    @Override
    ResponseEntity<List<BadgesResponseDTO>> badgesGet(@ApiParam(value = "token that contains the application key",required = true) @RequestHeader(value = "X-Api-Token",required = true) String apiToken) {
        ApplicationModel application = applicationRepository.findByApiToken(apiToken);

        if (application != null) {
            try {
                List<BadgesResponseDTO> response = new ArrayList<>();

                for(BadgeModel badge : application.getAllBadgeModel()){
                    response.add(Converter.convert(badge));
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
    ResponseEntity<Void> badgesPost(@ApiParam(value = "token that contains the application key",required = true) @RequestHeader(value = "X-Api-Token",required = true) String apiToken, @ApiParam(value = "The new badge need to have a name and a description.",required = true) @Valid @RequestBody BadgesDTO badgesDTO) {
        ApplicationModel application = applicationRepository.findByApiToken(apiToken);

        if (application != null) {
            BadgeModel badgeModel = new BadgeModel();

            if(!badgesDTO.getDescription().isEmpty() || !badgesDTO.getName().isEmpty() ){
                badgeModel.setDescription(badgesDTO.getDescription());
                badgeModel.setName(badgesDTO.getName());
                badgeModel.setApplicationModel(application);
            }

            badgeRepository.save(badgeModel);
            return ResponseEntity.status(HttpStatus.CREATED).header("id", String.valueOf(badge.getId())).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Override
    ResponseEntity<Void> badgesIdDelete(@ApiParam(value = "token that contains the application key",required = true) @RequestHeader(value = "X-Api-Token",required = true) String apiToken, @ApiParam(value = "Badge id to delete",required = true) @PathVariable("id") Long id) {
        ApplicationModel application = applicationRepository.findByApiToken(apiToken);

        if (application != null) {
            BadgeModel badgeModel = this.badgeRepository.findByApplicationAndID(application, id);

            if (badgeModel == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            badgeModel.getUsers().clear();

            badgeRepository.delete(badgeModel);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Override
    ResponseEntity<Void> badgesIdPut(@ApiParam(value = "token that contains the application key",required = true) @RequestHeader(value = "X-Api-Token",required = true) String apiToken, @ApiParam(value = "BadgeModel id to update",required = true) @PathVariable("id") Long id, @ApiParam(value = "The badge must have a new name and a new description",required = true) @Valid @RequestBody BadgesDTO badgesDTO) {
        ApplicationModel application = applicationRepository.findByApiToken(apiToken);

        if (application != null) {
            BadgeModel badgeModel = this.badgeRepository.findByApplicationAndID(application, id);

            if (badgeModel == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            if(!badgesDTO.getDescription().isEmpty() && !badgesDTO.getName().isEmpty()){
                badgeModel.setDescription(badgesDTO.getDescription());
                badgeModel.setName(badgesDTO.getName());
            }

            badgeRepository.save(badgeModel);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
