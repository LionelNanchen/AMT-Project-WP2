package ch.heigvd.gamification.api.dto;

import java.util.Objects;
import ch.heigvd.gamification.api.dto.ValueDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * EventDTO
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-24T10:51:16.109+01:00")

public class EventDTO   {
  @JsonProperty("type")
  private String type = null;

  @JsonProperty("GamifiedUserId")
  private String gamifiedUserId = null;

  @JsonProperty("timestamp")
  private OffsetDateTime timestamp = null;

  @JsonProperty("properties")
  @Valid
  private List<ValueDTO> properties = null;

  public EventDTO type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(value = "")


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public EventDTO gamifiedUserId(String gamifiedUserId) {
    this.gamifiedUserId = gamifiedUserId;
    return this;
  }

  /**
   * Get gamifiedUserId
   * @return gamifiedUserId
  **/
  @ApiModelProperty(value = "")


  public String getGamifiedUserId() {
    return gamifiedUserId;
  }

  public void setGamifiedUserId(String gamifiedUserId) {
    this.gamifiedUserId = gamifiedUserId;
  }

  public EventDTO timestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * Get timestamp
   * @return timestamp
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public EventDTO properties(List<ValueDTO> properties) {
    this.properties = properties;
    return this;
  }

  public EventDTO addPropertiesItem(ValueDTO propertiesItem) {
    if (this.properties == null) {
      this.properties = new ArrayList<ValueDTO>();
    }
    this.properties.add(propertiesItem);
    return this;
  }

  /**
   * Get properties
   * @return properties
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<ValueDTO> getProperties() {
    return properties;
  }

  public void setProperties(List<ValueDTO> properties) {
    this.properties = properties;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EventDTO eventDTO = (EventDTO) o;
    return Objects.equals(this.type, eventDTO.type) &&
        Objects.equals(this.gamifiedUserId, eventDTO.gamifiedUserId) &&
        Objects.equals(this.timestamp, eventDTO.timestamp) &&
        Objects.equals(this.properties, eventDTO.properties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, gamifiedUserId, timestamp, properties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EventDTO {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    gamifiedUserId: ").append(toIndentedString(gamifiedUserId)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    properties: ").append(toIndentedString(properties)).append("\n");
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

