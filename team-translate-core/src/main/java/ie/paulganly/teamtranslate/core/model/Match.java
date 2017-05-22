package ie.paulganly.teamtranslate.core.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Match {
	@JsonProperty("phrase")
	private String phrase = null;

	@JsonProperty("percentageMatch")
	private Long percentageMatch = null;

	@JsonProperty("translation")
	private String translation = null;

	public Match(Translation translation, String orignalPhrase, Language translateFrom) {
		this.phrase = orignalPhrase;
		switch (translateFrom) {
		case ENGLISH:
			this.translation = translation.getIrish();
			break;
		case IRISH:
			this.translation = translation.getEnglish();
			break;
		}
		this.percentageMatch = generatePercentageMatch(this.translation, orignalPhrase);
	}

	private Long generatePercentageMatch(String translation2, String orignalPhrase) {
		return 50L;
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

	public static List<Match> generateMatchListFromTranslationFound(List<Translation> translations,
			String orignalPhrase, Language translateFrom) {
		List<Match> matches = new ArrayList<Match>();
		for (Translation translation : translations) {
			matches.add(new Match(translation, orignalPhrase, translateFrom));
		}
		return matches;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Match match = (Match) o;
		return Objects.equals(this.phrase, match.phrase) && Objects.equals(this.percentageMatch, match.percentageMatch)
				&& Objects.equals(this.translation, match.translation);
	}

	@Override
	public int hashCode() {
		return Objects.hash(phrase, percentageMatch, translation);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Match {\n");
		sb.append("    phrase: ").append(toIndentedString(phrase)).append("\n");
		sb.append("    percentageMatch: ").append(toIndentedString(percentageMatch)).append("\n");
		sb.append("    translation: ").append(toIndentedString(translation)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}

}
