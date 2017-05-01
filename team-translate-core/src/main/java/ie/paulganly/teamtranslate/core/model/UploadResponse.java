package ie.paulganly.teamtranslate.core.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class UploadResponse   {
  @JsonProperty("propertiesFile")
  private File propertiesFile = null;

  @JsonProperty("matchesFound")
  private Long matchesFound = null;

  @JsonProperty("unmatchedPhrases")
  private List<UnmatchedArray> unmatchedPhrases = new ArrayList<UnmatchedArray>();

  public UploadResponse propertiesFile(File propertiesFile) {
    this.propertiesFile = propertiesFile;
    return this;
  }

   /**
   * Get propertiesFile
   * @return propertiesFile
  **/
  @ApiModelProperty(value = "")
  public File getPropertiesFile() {
    return propertiesFile;
  }

  public void setPropertiesFile(File propertiesFile) {
    this.propertiesFile = propertiesFile;
  }

  public UploadResponse matchesFound(Long matchesFound) {
    this.matchesFound = matchesFound;
    return this;
  }

   /**
   * Get matchesFound
   * @return matchesFound
  **/
  @ApiModelProperty(value = "")
  public Long getMatchesFound() {
    return matchesFound;
  }

  public void setMatchesFound(Long matchesFound) {
    this.matchesFound = matchesFound;
  }

  public UploadResponse unmatchedPhrases(List<UnmatchedArray> unmatchedPhrases) {
    this.unmatchedPhrases = unmatchedPhrases;
    return this;
  }

  public UploadResponse addUnmatchedPhrasesItem(UnmatchedArray unmatchedPhrasesItem) {
    this.unmatchedPhrases.add(unmatchedPhrasesItem);
    return this;
  }

   /**
   * Get unmatchedPhrases
   * @return unmatchedPhrases
  **/
  @ApiModelProperty(value = "")
  public List<UnmatchedArray> getUnmatchedPhrases() {
    return unmatchedPhrases;
  }

  public void setUnmatchedPhrases(List<UnmatchedArray> unmatchedPhrases) {
    this.unmatchedPhrases = unmatchedPhrases;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UploadResponse uploadResponse = (UploadResponse) o;
    return Objects.equals(this.propertiesFile, uploadResponse.propertiesFile) &&
        Objects.equals(this.matchesFound, uploadResponse.matchesFound) &&
        Objects.equals(this.unmatchedPhrases, uploadResponse.unmatchedPhrases);
  }

  @Override
  public int hashCode() {
    return Objects.hash(propertiesFile, matchesFound, unmatchedPhrases);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UploadResponse {\n");
    
    sb.append("    propertiesFile: ").append(toIndentedString(propertiesFile)).append("\n");
    sb.append("    matchesFound: ").append(toIndentedString(matchesFound)).append("\n");
    sb.append("    unmatchedPhrases: ").append(toIndentedString(unmatchedPhrases)).append("\n");
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

