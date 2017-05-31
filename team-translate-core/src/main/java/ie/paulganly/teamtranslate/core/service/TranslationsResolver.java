package ie.paulganly.teamtranslate.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;

import ie.paulganly.teamtranslate.core.model.Language;
import ie.paulganly.teamtranslate.core.model.Match;
import ie.paulganly.teamtranslate.core.model.Translation;

public interface TranslationsResolver {

	public Map<String, Map<Language, String>> parseInputFile(MultipartFile fileIn, Language lang);

	public Map<String, Map<Language, String>> fillExactMatchTranslationsFromRepo(Map<String, Map<Language, String>> translatonsMap, Language lang);

	public String[] buildArrayOfUnmatchedPhrases(Map<String, Map<Language, String>> translatonsMap, Language lang);

	public List<Match> mapFoundListToListMatches(String toTranslate, Language lang, List<Translation> foundTranslations);

	public FileSystemResource buildPropertiesFileFromMap(Map<String, Map<Language, String>> translatonsMap, Language lang);

	public int getTotalNumberOfPhrases(Map<String, Map<Language, String>> translationMap);

	public int getTotalNumberOfExactMatches(Map<String, Map<Language, String>> translationMap, Language translateFrom);

}


