package ie.paulganly.teamtranslate.core.dao;

import org.springframework.data.repository.CrudRepository;
import ie.paulganly.teamtranslate.core.model.Translation;
import java.util.List;

public interface TranslationsRepository extends CrudRepository<Translation, Long> {

	List<Translation> findTop5ByEnglishContaining(String english);

	List<Translation> findTop5ByIrishContaining(String irish);

}