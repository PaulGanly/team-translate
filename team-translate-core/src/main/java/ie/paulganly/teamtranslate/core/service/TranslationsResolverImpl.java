package ie.paulganly.teamtranslate.core.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ie.paulganly.teamtranslate.core.dao.TranslationsRepository;
import ie.paulganly.teamtranslate.core.model.Language;
import ie.paulganly.teamtranslate.core.model.Match;
import ie.paulganly.teamtranslate.core.model.Translation;

@Service
public class TranslationsResolverImpl implements TranslationsResolver {

	@Autowired
	private TranslationsRepository repo;

	@Override
	public Map<String, Map<Language, String>> parseInputFile(MultipartFile fileIn, Language lang) {

		Properties properties = new Properties();
		Map<String, Map<Language, String>> outputMap = new HashMap<>();

		try{
			properties.load(fileIn.getInputStream());
		} catch(IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Enumeration<?> elements = properties.propertyNames();
		while(elements.hasMoreElements()){
			Map<Language, String> translationMap = new HashMap<>();
			String token = (String) elements.nextElement();

			if(Language.ENGLISH.equals(lang)){
				translationMap.put(Language.ENGLISH, properties.getProperty(token));
				translationMap.put(Language.IRISH, "");
			}
			else{
				translationMap.put(Language.ENGLISH, "");
				translationMap.put(Language.IRISH, properties.getProperty(token));
			}

			outputMap.put(token, translationMap);
		}

		return outputMap;
	}

	@Override
	public Map<String, Map<Language, String>> fillExactMatchTranslationsFromRepo(Map<String, Map<Language, String>> translationsMap, Language lang) {

		for(Entry<String, Map<Language, String>> entry : translationsMap.entrySet()){
			if(Language.ENGLISH.equals(lang)){
				Translation foundTranslation = repo.findTop1ByEnglish(entry.getValue().get(Language.ENGLISH));
				if(foundTranslation != null){
					entry.getValue().put(Language.IRISH, foundTranslation.getIrish());
				}
			}
			else{
				Translation foundTranslation = repo.findTop1ByIrish(entry.getValue().get(Language.IRISH));
				if(foundTranslation != null){
					entry.getValue().put(Language.ENGLISH, foundTranslation.getIrish());
				}
			}
		}

		return translationsMap;
	}

	@Override
	public String[] buildArrayOfUnmatchedPhrases(Map<String, Map<Language, String>> translationsMap, Language lang) {

		List<String> unmatchedTranslations = new ArrayList<String>();

		for(Entry<String, Map<Language, String>> entry : translationsMap.entrySet()){

			if(Language.ENGLISH.equals(lang)){

				if(entry.getValue().get(Language.IRISH).isEmpty()){
					String toTranslate = entry.getValue().get(Language.ENGLISH);
					unmatchedTranslations.add(toTranslate);
				}

			}
			else{

				if(entry.getValue().get(Language.ENGLISH).isEmpty()){
					String toTranslate = entry.getValue().get(Language.IRISH);
					unmatchedTranslations.add(toTranslate);
				}
			}
		}

		return unmatchedTranslations.toArray(new String[unmatchedTranslations.size()]);
	}

	@Override
	public List<Match> mapFoundListToListMatches(String toTranslate, Language lang, List<Translation> foundTranslations) {

		List<Match> matches = new ArrayList<>();

		for(Translation translation : foundTranslations){
			Match currentMatch;
			if(Language.ENGLISH.equals(lang)){
				currentMatch = new Match(translation.getEnglish(), generatePercentageMatch(toTranslate, translation.getIrish()), translation.getIrish());
			}
			else{
				currentMatch = new Match(translation.getIrish(), generatePercentageMatch(toTranslate, translation.getIrish()), translation.getEnglish());
			}
			matches.add(currentMatch);
		}
		return matches;
	}

	private Long generatePercentageMatch(String orignalPhrase, String translation) {
		return 50L;
	}

	@Override
	public FileSystemResource buildPropertiesFileFromMap(Map<String, Map<Language, String>> translationsMap, Language lang) {

		Properties prop = new Properties();
		File outFile = new File("translations.properties");

		try(OutputStream output = new FileOutputStream(outFile)){

			for(Entry<String, Map<Language, String>> entry : translationsMap.entrySet()){
				if(Language.ENGLISH.equals(lang)){

					prop.setProperty(entry.getKey(), entry.getValue().get(Language.IRISH));

				}
				else{

					prop.setProperty(entry.getKey(), entry.getValue().get(Language.ENGLISH));
				}
			}

			prop.store(output, null);

		} catch(IOException io){
			io.printStackTrace();
		}

		return new FileSystemResource(outFile);

	}

	@Override
	public int getTotalNumberOfPhrases(Map<String, Map<Language, String>> translationMap) {
		return translationMap.size();
	}

	@Override
	public int getTotalNumberOfExactMatches(Map<String, Map<Language, String>> translationMap, Language translateFrom) {
		int numberOfFoundTranslations = 0;

		for(Entry<String, Map<Language, String>> entry : translationMap.entrySet()){

			if(Language.ENGLISH.equals(translateFrom)){
				if(!entry.getValue().get(Language.IRISH).isEmpty()){
					numberOfFoundTranslations++;
				}
			}
			else{
				if(!entry.getValue().get(Language.ENGLISH).isEmpty()){
					numberOfFoundTranslations++;
				}
			}
		}

		return numberOfFoundTranslations;
	}

}
