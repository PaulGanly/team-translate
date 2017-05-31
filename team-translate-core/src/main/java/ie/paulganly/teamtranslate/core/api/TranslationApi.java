package ie.paulganly.teamtranslate.core.api;

import java.util.List;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import ie.paulganly.teamtranslate.core.model.Match;
import ie.paulganly.teamtranslate.core.model.UploadResponse;

public interface TranslationApi {

	@RequestMapping(value = "/translation/find", produces = {"application/json"}, method = RequestMethod.GET)
	ResponseEntity<List<Match>> findTranslationsForPhrase(@RequestParam(value = "phrase", required = true) String phrase,
	    @RequestParam(value = "language", required = true, defaultValue = "english") String language);

	@RequestMapping(value = "/translation/input", produces = {"application/json"}, consumes = {
	    "application/x-www-form-urlencoded"}, method = RequestMethod.POST)
	ResponseEntity<String> inputTranslationWithForm(@ModelAttribute(value = "english") String english, @ModelAttribute(value = "irish") String irish,
	    @ModelAttribute(value = "context") String context);

	@RequestMapping(value = "/translation/uploadFile", produces = {"application/json"}, consumes = {"multipart/form-data"}, method = RequestMethod.POST)
	ResponseEntity<UploadResponse> uploadFile(@RequestParam(value = "language", required = true, defaultValue = "english") String language,
	    @RequestPart(value = "file", required = false) MultipartFile file);

	@RequestMapping(value = "/translation/downloadTranslatedFile", produces = {"application/json"}, consumes = {"multipart/form-data"}, method = RequestMethod.POST)
	ResponseEntity<FileSystemResource> downloadTranslatedFile(@RequestParam(value = "language", required = true, defaultValue = "english") String language,
	    @RequestPart(value = "file", required = false) MultipartFile file);

}