package ch.heigvd.gamification.api.dto;

import java.util.Objects;
import ch.heigvd.gamification.api.dto.ConditionDTO;
import ch.heigvd.gamification.api.dto.ValueDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RuleDTO
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-24T10:51:16.109+01:00")

public class RuleDTO   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("quantity")
  private Integer quantity = null;

  @JsonProperty("pointsScaleID")
  private Long pointsScaleID = null;

  @JsonProperty("badgeID")
  private Long badgeID = null;

  @JsonProperty("conditions")
  @Valid
  private List<ConditionDTO> conditions = null;

  @JsonProperty("properties")
  private ValueDTO properties = null;

  public RuleDTO name(String name) {
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

  public RuleDTO type(String type) {
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

  public RuleDTO quantity(Integer quantity) {
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

  public RuleDTO pointsScaleID(Long pointsScaleID) {
    this.pointsScaleID = pointsScaleID;
    return this;
  }

  /**
   * Get pointsScaleID
   * @return pointsScaleID
  **/
  @ApiModelProperty(value = "")


  public Long getPointsScaleID() {
    return pointsScaleID;
  }

  public void setPointsScaleID(Long pointsScaleID) {
    this.pointsScaleID = pointsScaleID;
  }

  public RuleDTO badgeID(Long badgeID) {
    this.badgeID = badgeID;
    return this;
  }

  /**
   * Get badgeID
   * @return badgeID
  **/
  @ApiModelProperty(value = "")


  public Long getBadgeID() {
    return badgeID;
  }

  public void setBadgeID(Long badgeID) {
    this.badgeID = badgeID;
  }

  public RuleDTO conditions(List<ConditionDTO> conditions) {
    this.conditions = conditions;
    return this;
  }

  public RuleDTO addConditionsItem(ConditionDTO conditionsItem) {
    if (this.conditions == null) {
      this.conditions = new ArrayList<ConditionDTO>();
    }
    this.conditions.add(conditionsItem);
    return this;
  }

  /**
   * Get conditions
   * @return conditions
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<ConditionDTO> getConditions() {
    return conditions;
  }

  public void setConditions(List<ConditionDTO> conditions) {
    this.conditions = conditions;
  }

  public RuleDTO properties(ValueDTO properties) {
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
    RuleDTO ruleDTO = (RuleDTO) o;
    return Objects.equals(this.name, ruleDTO.name) &&
        Objects.equals(this.type, ruleDTO.type) &&
        Objects.equals(this.quantity, ruleDTO.quantity) &&
        Objects.equals(this.pointsScaleID, ruleDTO.pointsScaleID) &&
        Objects.equals(this.badgeID, ruleDTO.badgeID) &&
        Objects.equals(this.conditions, ruleDTO.conditions) &&
        Objects.equals(this.properties, ruleDTO.properties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, type, quantity, pointsScaleID, badgeID, conditions, properties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RuleDTO {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("    pointsScaleID: ").append(toIndentedString(pointsScaleID)).append("\n");
    sb.append("    badgeID: ").append(toIndentedString(badgeID)).append("\n");
    sb.append("    conditions: ").append(toIndentedString(conditions)).append("\n");
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

