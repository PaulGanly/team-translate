package ie.paulganly.teamtranslate.core.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UnmatchedPhrase {

	@JsonProperty("phrase")
	private String phrase = null;

	@JsonProperty("closeMatches")
	private List<Match> closeMatches = new ArrayList<Match>();

	public UnmatchedPhrase(String phrase) {
		this.phrase = phrase;
	}

	public UnmatchedPhrase phrase(String phrase) {
		this.phrase = phrase;
		return this;
	}

	public String getPhrase() {
		return phrase;
	}

	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}

	public UnmatchedPhrase closeMatches(List<Match> closeMatches) {
		this.closeMatches = closeMatches;
		return this;
	}

	public UnmatchedPhrase addCloseMatchesItem(Match closeMatchesItem) {
		this.closeMatches.add(closeMatchesItem);
		return this;
	}

	public List<Match> getCloseMatches() {
		return closeMatches;
	}

	public void setCloseMatches(List<Match> closeMatches) {
		this.closeMatches = closeMatches;
	}
}
