package ie.paulganly.teamtranslate.core.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import ie.paulganly.teamtranslate.core.dao.TranslationsRepository;
import ie.paulganly.teamtranslate.core.model.Language;
import ie.paulganly.teamtranslate.core.model.Match;
import ie.paulganly.teamtranslate.core.model.Translation;
import ie.paulganly.teamtranslate.core.model.UploadResponse;
import ie.paulganly.teamtranslate.core.service.TranslationsResolver;

@Controller
public class TranslationApiController implements TranslationApi {

	@Autowired
	private TranslationsRepository repo;

	@Autowired
	private TranslationsResolver resolver;

	public ResponseEntity<List<Match>> findTranslationsForPhrase(@RequestParam(value = "phrase", required = true) String phrase,
	    																												 @RequestParam(value = "language", required = true, defaultValue = "ENGLISH") String language) {

		if(Language.ENGLISH.equals(Language.valueOf(language))){
			List<Match> matchList = new ArrayList<Match>();
			List<Translation> foundTranslationsList = repo.findTop5ByEnglishContaining(phrase);
			matchList.addAll(resolver.mapFoundListToListMatches(phrase, Language.ENGLISH, foundTranslationsList));
			return new ResponseEntity<List<Match>>(matchList, HttpStatus.OK);
		}
		else{
			List<Match> matchList = new ArrayList<Match>();
			List<Translation> foundTranslationsList = repo.findTop5ByIrishContaining(phrase);
			matchList.addAll(resolver.mapFoundListToListMatches(phrase, Language.IRISH, foundTranslationsList));
			return new ResponseEntity<List<Match>>(matchList, HttpStatus.OK);
		}

	}

	public ResponseEntity<String> inputTranslationWithForm(@ModelAttribute(value = "english") String english,
	    																									 @ModelAttribute(value = "irish") String irish,
	    																									 @ModelAttribute(value = "context") String context) {
		Translation n = new Translation();
		n.setEnglish(english);
		n.setIrish(irish);
		n.setContext(context);
		repo.save(n);
		return new ResponseEntity<String>("Saved: " + english, HttpStatus.OK);
	}

	public ResponseEntity<UploadResponse> uploadFile(@RequestParam(value = "language", required = true) String language,
																									 @RequestParam(value = "file", required = true) MultipartFile file) {

		Language translateFrom = Language.valueOf(language);

		Map<String, Map<Language, String>> translationMap = resolver.parseInputFile(file, translateFrom);
		translationMap = resolver.fillExactMatchTranslationsFromRepo(translationMap, translateFrom);

		UploadResponse response = new UploadResponse(file.getName(), resolver.getTotalNumberOfPhrases(translationMap),
		    resolver.getTotalNumberOfExactMatches(translationMap, translateFrom), resolver.buildArrayOfUnmatchedPhrases(translationMap, translateFrom));

		return new ResponseEntity<UploadResponse>(response, HttpStatus.OK);
	}


	public ResponseEntity<FileSystemResource> downloadTranslatedFile(@RequestParam(value = "language", required = true) String language,
	    																												 @RequestPart(value = "file", required = false) MultipartFile file) {

		Language translateFrom = Language.valueOf(language);

		Map<String, Map<Language, String>> translationMap = resolver.parseInputFile(file, translateFrom);
		translationMap = resolver.fillExactMatchTranslationsFromRepo(translationMap, translateFrom);

		FileSystemResource returnedFile = resolver.buildPropertiesFileFromMap(translationMap, translateFrom);

		return new ResponseEntity<FileSystemResource>(returnedFile, HttpStatus.OK);
	}

}
