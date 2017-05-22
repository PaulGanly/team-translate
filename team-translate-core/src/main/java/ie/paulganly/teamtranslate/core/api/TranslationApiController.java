package ie.paulganly.teamtranslate.core.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ie.paulganly.teamtranslate.core.dao.TranslationsRepository;
import ie.paulganly.teamtranslate.core.model.Language;
import ie.paulganly.teamtranslate.core.model.Match;
import ie.paulganly.teamtranslate.core.model.Translation;
import ie.paulganly.teamtranslate.core.model.UploadResponse;

@Controller
public class TranslationApiController implements TranslationApi {

	@Autowired
	private TranslationsRepository repo;

	public ResponseEntity<List<Match>> findTranslationsForPhrase(
			@RequestParam(value = "phrase", required = true) String phrase,
			@RequestParam(value = "language", required = true, defaultValue = "ENGLISH") String language) {

		Language translateFrom = Language.valueOf(language);

		if (translateFrom.equals(Language.ENGLISH)) {
			List<Match> matchList = new ArrayList<Match>();
			List<Translation> foundTranslationsList = repo.findTop5ByEnglishContaining(phrase);
			matchList.addAll(Match.generateMatchListFromTranslationFound(foundTranslationsList, phrase, translateFrom));
			return new ResponseEntity<List<Match>>(matchList, HttpStatus.OK);
		} else {
			List<Match> matchList = new ArrayList<Match>();
			List<Translation> foundTranslationsList = repo.findTop5ByIrishContaining(phrase);
			matchList.addAll(Match.generateMatchListFromTranslationFound(foundTranslationsList, phrase, translateFrom));
			return new ResponseEntity<List<Match>>(matchList, HttpStatus.OK);
		}
	}

	public ResponseEntity<Translation> getTranslationById(@PathVariable("id") Long id) {
		// do some magic!
		return new ResponseEntity<Translation>(HttpStatus.OK);
	}

	public ResponseEntity<Void> inputTranslationWithForm(@ModelAttribute(value = "english") String english,
			@ModelAttribute(value = "irish") String irish, @ModelAttribute(value = "context") String context) {
		Translation n = new Translation();
		n.setEnglish(english);
		n.setIrish(irish);
		n.setContext(context);
		repo.save(n);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	public ResponseEntity<UploadResponse> uploadFile(@RequestParam(value = "language", required = true) String language,
			@RequestParam("file") MultipartFile file) {
		// do some magic!
		System.out.println("Upload file " + language + " " + file.getName());
		return new ResponseEntity<UploadResponse>(HttpStatus.OK);
	}

}
