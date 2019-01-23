package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.BadgesResponseDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * EndUserSummaryDTO
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-23T21:24:14.760Z")

public class EndUserSummaryDTO   {
  @JsonProperty("userId")
  private Long userId = null;

  @JsonProperty("gamifiedUserId")
  private String gamifiedUserId = null;

  @JsonProperty("badgesWon")
  @Valid
  private List<BadgesResponseDTO> badgesWon = null;

  public EndUserSummaryDTO userId(Long userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  **/
  @ApiModelProperty(value = "")


  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public EndUserSummaryDTO gamifiedUserId(String gamifiedUserId) {
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

  public EndUserSummaryDTO badgesWon(List<BadgesResponseDTO> badgesWon) {
    this.badgesWon = badgesWon;
    return this;
  }

  public EndUserSummaryDTO addBadgesWonItem(BadgesResponseDTO badgesWonItem) {
    if (this.badgesWon == null) {
      this.badgesWon = new ArrayList<BadgesResponseDTO>();
    }
    this.badgesWon.add(badgesWonItem);
    return this;
  }

  /**
   * Get badgesWon
   * @return badgesWon
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<BadgesResponseDTO> getBadgesWon() {
    return badgesWon;
  }

  public void setBadgesWon(List<BadgesResponseDTO> badgesWon) {
    this.badgesWon = badgesWon;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EndUserSummaryDTO endUserSummaryDTO = (EndUserSummaryDTO) o;
    return Objects.equals(this.userId, endUserSummaryDTO.userId) &&
        Objects.equals(this.gamifiedUserId, endUserSummaryDTO.gamifiedUserId) &&
        Objects.equals(this.badgesWon, endUserSummaryDTO.badgesWon);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, gamifiedUserId, badgesWon);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EndUserSummaryDTO {\n");
    
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    gamifiedUserId: ").append(toIndentedString(gamifiedUserId)).append("\n");
    sb.append("    badgesWon: ").append(toIndentedString(badgesWon)).append("\n");
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

