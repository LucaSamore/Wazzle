package wazzle.model.maingame;

import java.util.Optional;
import java.util.Set;

public interface GridValidator {
	
	Optional<Set<String>> validate(final Set<Letter> letters);
	
	//This method must be used for tests only!
	Set<String> validateForTest(final Set<Letter> letters);
}
