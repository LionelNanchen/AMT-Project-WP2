package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.PointScaleDTO;
import ch.heigvd.gamification.api.dto.PointScaleIdDTO;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.PointScale;
import ch.heigvd.gamification.repository.ApplicationRepository;
import ch.heigvd.gamification.repository.PointScaleRepository;
import ch.heigvd.gamification.util.ModelToDTOConverter;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Authors: Amrani Kamil, Nanchen Lionel, Nicole Olivier, Reka Mentor
 * AMT WP2 2018-2019
 */
@RestController
public class PointScaleController implements PointScaleApi {

    @Autowired
    private  ApplicationRepository applicationRepository;

    @Autowired
    private PointScaleRepository pointScaleRepository;

    @Override
    public ResponseEntity<List<PointScaleIdDTO>> pointScaleGet(@ApiParam(value = "token that contains the application key" ,required=true) @RequestHeader(value="X-Api-Key", required=true) String xApiKey) {
        Application application = applicationRepository.findByAppKey(xApiKey);

        if (application != null) {
            List<PointScaleIdDTO> response = new ArrayList<>();

            for (PointScale pointScale : application.getPointScales()) {
                response.add(ModelToDTOConverter.convert(pointScale));
            }

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Override
    public ResponseEntity<Void> pointScaleIdDelete(@ApiParam(value = "token that contains the application key" ,required=true) @RequestHeader(value="X-Api-Key", required=true) String xApiKey,@ApiParam(value = "PointScaleModel id to delete",required=true ) @PathVariable("id") Long id) {
        Application application = applicationRepository.findByAppKey(xApiKey);

        if (application != null) {
            PointScale pointScale = pointScaleRepository.findByApplicationAndId(application, id);

            if (pointScale == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            pointScaleRepository.delete(pointScale);
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Override
    public ResponseEntity<Void> pointScaleIdPut(@ApiParam(value = "token that contains the application key" ,required=true) @RequestHeader(value="X-Api-Key", required=true) String xApiKey,@ApiParam(value = "PointScaleModel id to update",required=true ) @PathVariable("id") Long id,@ApiParam(value = "The pointScale must have a new value" ,required=true )  @Valid @RequestBody PointScaleDTO body) {
        Application application = applicationRepository.findByAppKey(xApiKey);

        if (application != null) {
            PointScale pointScale = this.pointScaleRepository.findByApplicationAndId(application, id);

            if (pointScale == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            pointScale.setName(body.getPointScaleName());
            pointScaleRepository.save(pointScale);
            return  ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Override
    public ResponseEntity<Void> pointScalePost(@ApiParam(value = "token that contains the application key" ,required=true) @RequestHeader(value="X-Api-Key", required=true) String xApiKey,@ApiParam(value = "The new pointScale" ,required=true )  @Valid @RequestBody PointScaleDTO body) {
        Application application = applicationRepository.findByAppKey(xApiKey);

        if (application != null) {
            PointScale pointScale = new PointScale(body.getPointScaleName(), application);
            pointScaleRepository.save(pointScale);
            return ResponseEntity.status(HttpStatus.CREATED).header("id", String.valueOf(pointScale.getId())).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}