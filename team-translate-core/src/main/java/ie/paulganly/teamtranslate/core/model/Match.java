package ie.paulganly.teamtranslate.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Match {
	@JsonProperty("phrase")
	private String phrase = null;

	@JsonProperty("percentageMatch")
	private Long percentageMatch = null;

	@JsonProperty("translation")
	private String translation = null;

	public Match(String phrase, Long percentageMatch, String translation) {
		super();
		this.phrase = phrase;
		this.percentageMatch = percentageMatch;
		this.translation = translation;
	}

	public Match phrase(String phrase) {
		this.phrase = phrase;
		return this;
	}

	public String getPhrase() {
		return phrase;
	}

	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}

	public Match percentageMatch(Long percentageMatch) {
		this.percentageMatch = percentageMatch;
		return this;
	}

	public Long getPercentageMatch() {
		return percentageMatch;
	}

	public void setPercentageMatch(Long percentageMatch) {
		this.percentageMatch = percentageMatch;
	}

	public Match translation(String translation) {
		this.translation = translation;
		return this;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

}
