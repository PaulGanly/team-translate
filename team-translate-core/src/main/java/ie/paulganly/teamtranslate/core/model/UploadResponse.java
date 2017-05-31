package ie.paulganly.teamtranslate.core.model;
public class UploadResponse {

	private String fileName;
	private int totalPhrases;
	private int numberOfExactMatches;
	private String[] unmatchedPhrases;

	public UploadResponse(String fileName, int totalPhrases, int numberOfExactMatches, String[] unmatchedPhrases) {
		super();
		this.fileName = fileName;
		this.totalPhrases = totalPhrases;
		this.numberOfExactMatches = numberOfExactMatches;
		this.unmatchedPhrases = unmatchedPhrases;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getTotalPhrases() {
		return totalPhrases;
	}

	public void setTotalPhrases(int totalPhrases) {
		this.totalPhrases = totalPhrases;
	}

	public int getNumberOfExactMatches() {
		return numberOfExactMatches;
	}

	public void setNumberOfExactMatches(int numberOfExactMatches) {
		this.numberOfExactMatches = numberOfExactMatches;
	}

	public String[] getUnmatchedPhrases() {
		return unmatchedPhrases;
	}

	public void setUnmatchedPhrases(String[] unmatchedPhrases) {
		this.unmatchedPhrases = unmatchedPhrases;
	}

}

