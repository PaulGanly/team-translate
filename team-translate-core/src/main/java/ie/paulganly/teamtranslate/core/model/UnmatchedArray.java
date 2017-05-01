package ie.paulganly.teamtranslate.core.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class UnmatchedArray   {
  @JsonProperty("phrase")
  private String phrase = null;

  @JsonProperty("closeMatches")
  private List<Match> closeMatches = new ArrayList<Match>();

  public UnmatchedArray phrase(String phrase) {
    this.phrase = phrase;
    return this;
  }

   /**
   * Get phrase
   * @return phrase
  **/
  @ApiModelProperty(value = "")
  public String getPhrase() {
    return phrase;
  }

  public void setPhrase(String phrase) {
    this.phrase = phrase;
  }

  public UnmatchedArray closeMatches(List<Match> closeMatches) {
    this.closeMatches = closeMatches;
    return this;
  }

  public UnmatchedArray addCloseMatchesItem(Match closeMatchesItem) {
    this.closeMatches.add(closeMatchesItem);
    return this;
  }

   /**
   * Get closeMatches
   * @return closeMatches
  **/
  @ApiModelProperty(value = "")
  public List<Match> getCloseMatches() {
    return closeMatches;
  }

  public void setCloseMatches(List<Match> closeMatches) {
    this.closeMatches = closeMatches;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UnmatchedArray unmatchedArray = (UnmatchedArray) o;
    return Objects.equals(this.phrase, unmatchedArray.phrase) &&
        Objects.equals(this.closeMatches, unmatchedArray.closeMatches);
  }

  @Override
  public int hashCode() {
    return Objects.hash(phrase, closeMatches);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UnmatchedArray {\n");
    
    sb.append("    phrase: ").append(toIndentedString(phrase)).append("\n");
    sb.append("    closeMatches: ").append(toIndentedString(closeMatches)).append("\n");
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

