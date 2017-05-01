package ie.paulganly.teamtranslate.core;
//package ie.revenue.translations.core;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import ie.revenue.translations.core.model.Translation;
//
//@RestController
//public class RestServiceController {
//
//	@Autowired
//	private TranslationsRepository repo;
//
//	static final Logger logger = LogManager.getLogger(RestServiceController.class.getName());
//	
//	@RequestMapping("/translation/read")
//	@ResponseBody
//	public Translation readTranslationById(@RequestParam (value="id") long id) {
//			return repo.findOne(id);
//	}
//	
//	@RequestMapping("/translation/search_english")
//	@ResponseBody
//	public Translation readTranslationByEnglish(@RequestParam (value="term") String english) {
//			return repo.findByEnglish(english);
//	}
//	
//	@RequestMapping("/translation/search_irish")
//	@ResponseBody
//	public Translation readTranslationByIrish(@RequestParam (value="term") String irish) {
//			return repo.findByIrish(irish);
//	}
//
//	@RequestMapping("/translation/all")
//	@ResponseBody
//	public Iterable<Translation> readAllTranslation() {
//		return repo.findAll();
//	}
//	
//	@RequestMapping("/translation/add") 
//	@ResponseBody
//	public String addNewTranslation(@RequestParam String english, @RequestParam String irish, @RequestParam String context) {
//		Translation n = new Translation();
//		n.setEnglish(english);
//		n.setIrish(irish);
//		n.setContext(context);
//		repo.save(n);
//		return "Saved";
//	}
//
//}