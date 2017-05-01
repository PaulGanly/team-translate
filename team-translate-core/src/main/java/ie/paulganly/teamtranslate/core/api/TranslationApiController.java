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
import ie.paulganly.teamtranslate.core.model.Match;
import ie.paulganly.teamtranslate.core.model.Translation;
import ie.paulganly.teamtranslate.core.model.UploadResponse;
import io.swagger.annotations.ApiParam;

@Controller
public class TranslationApiController implements TranslationApi {
	
	@Autowired
	private TranslationsRepository repo;

    public ResponseEntity<List<Match>> findTranslationsForPhrase(
    		@ApiParam(value = "Phrase to be translated", required = true) 
    		@RequestParam(value = "phrase", required = true) String phrase,
    		@ApiParam(value = "Language phrase is written in", required = true, allowableValues = "ENGLISH, IRISH", defaultValue = "english") 
    		@RequestParam(value = "language", required = true, defaultValue="ENGLISH") String language) {
	
    	if("ENGLISH".equals(language)){
    		List<Match> matchList = new ArrayList<Match>();
    		matchList.add(repo.findByEnglish(phrase));
    		return new ResponseEntity<List<Match>>(matchList, HttpStatus.OK);
    	}else{
    		List<Match> matchList = new ArrayList<Match>();
    		matchList.add(repo.findByIrish(phrase));
    		return new ResponseEntity<List<Match>>(matchList, HttpStatus.OK);
    	}
    }

    public ResponseEntity<Translation> getTranslationById(
    		@ApiParam(value = "ID of translation to return",required=true ) 
    		@PathVariable("id") Long id) {
        // do some magic!
        return new ResponseEntity<Translation>(HttpStatus.OK);
    }

    public ResponseEntity<Void> inputTranslationWithForm(
    		@ApiParam(value = "Phrase in English", required=true ) 
    		@ModelAttribute(value="english")  String english,
	        @ApiParam(value = "Phrase in Irish", required=true ) 
    		@ModelAttribute(value="irish")  String irish,
	        @ApiParam(value = "Phrase Context", required=true ) 
    		@ModelAttribute(value="context")  String context) {
    	Translation n = new Translation();
		n.setEnglish(english);
		n.setIrish(irish);
		n.setContext(context);
		repo.save(n);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<UploadResponse> uploadFile(
    		@ApiParam(value = "Language file phrases are written in", required=true , allowableValues="ENGLISH, IRISH", defaultValue="english") 
    		@RequestParam(value="language", required=true)  String language,
    		@ApiParam(value = "file detail") 
    		@RequestParam("file") MultipartFile file) {
        // do some magic!
    	System.out.println("Upload file " + language + " " + file.getName());
        return new ResponseEntity<UploadResponse>(HttpStatus.OK);
    }

}
