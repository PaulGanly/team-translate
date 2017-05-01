package ie.paulganly.teamtranslate.core.dao;

import org.springframework.data.repository.CrudRepository;

import ie.paulganly.teamtranslate.core.model.Match;
import ie.paulganly.teamtranslate.core.model.Translation;

public interface TranslationsRepository extends CrudRepository<Translation, Long> {

	Match findByEnglish(String english);

	Match findByIrish(String irish);

}