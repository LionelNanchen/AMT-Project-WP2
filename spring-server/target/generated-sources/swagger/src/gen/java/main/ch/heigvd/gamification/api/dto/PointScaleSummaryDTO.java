package ch.heigvd.gamification.api.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PointScaleSummaryDTO
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-23T20:53:46.582+01:00")

public class PointScaleSummaryDTO   {
  @JsonProperty("pointScaleId")
  private Long pointScaleId = null;

  @JsonProperty("pointScaleName")
  private String pointScaleName = null;

  public PointScaleSummaryDTO pointScaleId(Long pointScaleId) {
    this.pointScaleId = pointScaleId;
    return this;
  }

  /**
   * Get pointScaleId
   * @return pointScaleId
  **/
  @ApiModelProperty(value = "")


  public Long getPointScaleId() {
    return pointScaleId;
  }

  public void setPointScaleId(Long pointScaleId) {
    this.pointScaleId = pointScaleId;
  }

  public PointScaleSummaryDTO pointScaleName(String pointScaleName) {
    this.pointScaleName = pointScaleName;
    return this;
  }

  /**
   * Get pointScaleName
   * @return pointScaleName
  **/
  @ApiModelProperty(value = "")


  public String getPointScaleName() {
    return pointScaleName;
  }

  public void setPointScaleName(String pointScaleName) {
    this.pointScaleName = pointScaleName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PointScaleSummaryDTO pointScaleSummaryDTO = (PointScaleSummaryDTO) o;
    return Objects.equals(this.pointScaleId, pointScaleSummaryDTO.pointScaleId) &&
        Objects.equals(this.pointScaleName, pointScaleSummaryDTO.pointScaleName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pointScaleId, pointScaleName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PointScaleSummaryDTO {\n");
    
    sb.append("    pointScaleId: ").append(toIndentedString(pointScaleId)).append("\n");
    sb.append("    pointScaleName: ").append(toIndentedString(pointScaleName)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

