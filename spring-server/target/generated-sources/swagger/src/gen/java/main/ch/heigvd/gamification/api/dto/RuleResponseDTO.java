package ch.heigvd.gamification.api.dto;

import java.util.Objects;
import ch.heigvd.gamification.api.dto.BadgesResponseDTO;
import ch.heigvd.gamification.api.dto.PointScaleSummaryDTO;
import ch.heigvd.gamification.api.dto.ValueDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RuleResponseDTO
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-24T13:22:00.036+01:00")

public class RuleResponseDTO   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("quantity")
  private Integer quantity = null;

  @JsonProperty("pointScale")
  private PointScaleSummaryDTO pointScale = null;

  @JsonProperty("badge")
  private BadgesResponseDTO badge = null;

  @JsonProperty("properties")
  private ValueDTO properties = null;

  public RuleResponseDTO id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public RuleResponseDTO name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RuleResponseDTO type(String type) {
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

  public RuleResponseDTO quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

   /**
   * Get quantity
   * @return quantity
  **/
  @ApiModelProperty(value = "")


  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public RuleResponseDTO pointScale(PointScaleSummaryDTO pointScale) {
    this.pointScale = pointScale;
    return this;
  }

   /**
   * Get pointScale
   * @return pointScale
  **/
  @ApiModelProperty(value = "")

  @Valid

  public PointScaleSummaryDTO getPointScale() {
    return pointScale;
  }

  public void setPointScale(PointScaleSummaryDTO pointScale) {
    this.pointScale = pointScale;
  }

  public RuleResponseDTO badge(BadgesResponseDTO badge) {
    this.badge = badge;
    return this;
  }

   /**
   * Get badge
   * @return badge
  **/
  @ApiModelProperty(value = "")

  @Valid

  public BadgesResponseDTO getBadge() {
    return badge;
  }

  public void setBadge(BadgesResponseDTO badge) {
    this.badge = badge;
  }

  public RuleResponseDTO properties(ValueDTO properties) {
    this.properties = properties;
    return this;
  }

   /**
   * Get properties
   * @return properties
  **/
  @ApiModelProperty(value = "")

  @Valid

  public ValueDTO getProperties() {
    return properties;
  }

  public void setProperties(ValueDTO properties) {
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
    RuleResponseDTO ruleResponseDTO = (RuleResponseDTO) o;
    return Objects.equals(this.id, ruleResponseDTO.id) &&
        Objects.equals(this.name, ruleResponseDTO.name) &&
        Objects.equals(this.type, ruleResponseDTO.type) &&
        Objects.equals(this.quantity, ruleResponseDTO.quantity) &&
        Objects.equals(this.pointScale, ruleResponseDTO.pointScale) &&
        Objects.equals(this.badge, ruleResponseDTO.badge) &&
        Objects.equals(this.properties, ruleResponseDTO.properties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, type, quantity, pointScale, badge, properties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RuleResponseDTO {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("    pointScale: ").append(toIndentedString(pointScale)).append("\n");
    sb.append("    badge: ").append(toIndentedString(badge)).append("\n");
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

