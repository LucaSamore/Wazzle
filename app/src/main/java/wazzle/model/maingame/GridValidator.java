package wazzle.model.maingame;

import java.util.Optional;
import java.util.Set;

public interface GridValidator {
	Optional<Set<String>> validate(final Set<Letter> letters);
}
