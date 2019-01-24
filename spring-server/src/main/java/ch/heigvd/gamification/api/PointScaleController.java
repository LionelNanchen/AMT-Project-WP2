//package ch.heigvd.gamification.api;
//
//import io.swagger.annotations.ApiParam;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.Valid;
//import java.util.List;
//
///**
// * Authors: Amrani Kamil, Nanchen Lionel, Nicole Olivier, Reka Mentor
// * AMT WP2 2018-2019
// */
//@RestController
//public class PointScaleController implements PointScaleApi {
//    private final ApplicationRepository applicationRepository;
//    private final PointScaleRepository pointScaleRepository;
//
//    public PointScaleController(ApplicationRepository applicationRepository, PointScaleRepository pointScaleRepository) {
//        this.applicationRepository = applicationRepository;
//        this.pointScaleRepository = pointScaleRepository;
//    }
//
//    @Override
//    ResponseEntity<List<PointScaleSummaryDTO>> pointScaleGet(@ApiParam(value = "token that contains the application key",required = true) @RequestHeader(value = "X-Api-Token",required = true) String apiToken) {
//        ApplicationModel application = applicationRepository.findByApiToken(apiToken);
//
//        if (application != null) {
//            List<PointScaleSummaryDTO> response = new ArrayList<PointScaleSummaryDTO>();
//
//            for (PointScaleModel pointScaleModel : application.getAllPointScaleModel()) {
//                response.add(Converter.convert(pointScaleModel));
//            }
//
//            return ResponseEntity.ok(response);
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//    }
//
//    @Override
//    ResponseEntity<Void> pointScaleIdDelete(@ApiParam(value = "token that contains the application key",required = true) @RequestHeader(value = "X-Api-Token",required = true) String apiToken, @ApiParam(value = "PointScaleModel id to delete",required = true) @PathVariable("id") Long id) {
//        ApplicationModel application = applicationRepository.findByApiToken(apiToken);
//
//        if (application != null) {
//            PointScaleModel pointScaleModel = this.pointScaleRepository.findByApplicationAndID(application, id);
//
//            if (pointScaleModel == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//
//            pointScaleRepository.delete(pointScaleModel);
//            return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//    }
//
//    @Override
//    ResponseEntity<Void> pointScaleIdPut(@ApiParam(value = "token that contains the application key",required = true) @RequestHeader(value = "X-Api-Token",required = true) String apiToken, @ApiParam(value = "PointScaleModel id to update",required = true) @PathVariable("id") Long id, @ApiParam(value = "The pointScale must have a new value",required = true) @Valid @RequestBody PointScaleDTO pointScaleDTO) {
//        ApplicationModel application = applicationRepository.findByApiToken(apiToken);
//
//        if (application != null) {
//            PointScaleModel pointScaleModel = this.pointScaleRepository.findByApplicationAndId(application, id);
//
//            if (pointScaleModel == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//
//            pointScaleModel.setName(pointScaleDTO.getPointScaleName());
//            pointScaleRepository.save(pointScaleModel);
//            return  ResponseEntity.status(HttpStatus.OK).build();
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//    }
//
//    @Override
//    ResponseEntity<Void> pointScalePost(@ApiParam(value = "token that contains the application key",required = true) @RequestHeader(value = "X-Api-Token",required = true) String apiToken, @ApiParam(value = "The new pointScale",required = true) @Valid @RequestBody PointScaleDTO pointScaleDTO) {
//        ApplicationModel application = applicationRepository.findByApiToken(apiToken);
//
//        if (application != null) {
//            PointScaleModel pointScaleModel = new PointScaleModel(application, pointScaleDTO.getPointScaleName());
//            pointScaleRepository.save(pointScaleModel);
//            // ok pointScale sucessfully created
//            return ResponseEntity.status(HttpStatus.CREATED).header("id", String.valueOf(ps.getId())).build();
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//    }
//}