package ie.paulganly.teamtranslate.core.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ie.paulganly.teamtranslate.core.model.Translation;

public interface TranslationsRepository extends CrudRepository<Translation, Long> {

	Translation findTop1ByEnglish(String english);

	Translation findTop1ByIrish(String irish);

	List<Translation> findTop5ByEnglishContaining(String english);

	List<Translation> findTop5ByIrishContaining(String irish);

}