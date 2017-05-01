package ie.paulganly.teamtranslate.core.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import ie.paulganly.teamtranslate.core.model.Match;
import ie.paulganly.teamtranslate.core.model.Translation;
import ie.paulganly.teamtranslate.core.model.UploadResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "translation", description = "The translation API")
public interface TranslationApi {

		@ApiOperation(	value = "Finds Translations for the given phrase", 
						notes = "Language of given phrase must be supplied", 
						response = Match.class, 
						responseContainer = "List", tags = {"translation", })
		@ApiResponses(	value = { 	@ApiResponse(code = 200, message = "successful operation", response = Match.class),
									@ApiResponse(code = 400, message = "Invalid value", response = Match.class) })
		@RequestMapping(	value = "/translation/find", 
							produces = { "application/json" }, 
							method = RequestMethod.GET)
	ResponseEntity<List<Match>> findTranslationsForPhrase(
			@ApiParam(value = "Phrase to be translated", required = true) 
			@RequestParam(value = "phrase", required = true) String phrase,
			@ApiParam(value = "Language phrase is written in", required = true, allowableValues = "ENGLISH, IRISH", defaultValue = "english") 
			@RequestParam(value = "language", required = true, defaultValue = "english") String language);
		
		

		@ApiOperation(	value = "Find translation by ID", 
						notes = "Returns a single translation", 
						response = Translation.class, tags = {"translation", })
		@ApiResponses(	value = { 	@ApiResponse(code = 200, message = "successful operation", response = Translation.class),
									@ApiResponse(code = 400, message = "Invalid ID supplied", response = Translation.class),
									@ApiResponse(code = 404, message = "Translation not found", response = Translation.class) })
		@RequestMapping(	value = "/translation/{id}", 
							produces = { "application/json" }, 
							method = RequestMethod.GET)
	ResponseEntity<Translation> getTranslationById(
			@ApiParam(value = "ID of translation to return", required = true) 
			@PathVariable("id") Long id);
		
		
		

		@ApiOperation(	value = "Input a new translation with form data", 
						notes = "", response = Void.class, tags = {"translation", })
		@ApiResponses(	value = { 	@ApiResponse(code = 200, message = "successful operation", response = Void.class),
									@ApiResponse(code = 405, message = "Invalid input", response = Void.class) })
		@RequestMapping(	value = "/translation/input", 
							produces = { "application/json" }, 
							consumes = {"application/x-www-form-urlencoded" },
							method = RequestMethod.POST)
	ResponseEntity<Void> inputTranslationWithForm(
			@ApiParam(value = "Phrase in English", required = true) 
			@ModelAttribute(value = "english") String english,
			@ApiParam(value = "Phrase in Irish", required = true) 
			@ModelAttribute(value = "irish") String irish,
			@ApiParam(value = "Phrase Context", required = true) 
			@ModelAttribute(value = "context") String context);
		
		
		

		@ApiOperation(	value = "Uploads a properties file for translation", 
						notes = "", response = UploadResponse.class, tags = {"translation", })
		@ApiResponses(	value = {	@ApiResponse(code = 200, message = "successful operation", response = UploadResponse.class) })
		@RequestMapping(	value = "/translation/uploadFile", 
							produces = { "application/json" }, 
							consumes = { "multipart/form-data" }, 
							method = RequestMethod.POST)
	ResponseEntity<UploadResponse> uploadFile(
			@ApiParam(value = "Language file phrases are written in", required = true, allowableValues = "ENGLISH, IRISH", defaultValue = "english") 
			@RequestPart(value = "language", required = true) String language,
			@ApiParam(value = "file detail") 
			@RequestPart("file") MultipartFile file);

}
