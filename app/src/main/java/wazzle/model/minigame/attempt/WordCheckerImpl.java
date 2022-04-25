package wazzle.model.minigame.attempt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class WordCheckerImpl implements WordChecker {

  private final String targetWord;
  private List<Character> lettersLeftToGuess;

  public WordCheckerImpl(final String word) {
    this.targetWord = word;
    this.lettersLeftToGuess = new ArrayList<>();
  }

  /** {@inheritDoc} */
  @Override
  public MiniGameWord computeAttemptResult(final String attempt) {
    List<WordElement> result = new ArrayList<>();
    this.lettersLeftToGuess =
        targetWord.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
    this.setAllCorrectLetterInTheRightPlace(attempt, result);
    this.setAllRemainingLetterState(result);
    return new MiniGameWordImpl(result);
  }

  /** {@inheritDoc} */
  @Override
  public boolean isCorrectWord(final String guessedWord) {
    return guessedWord.equals(this.targetWord);
  }

  private void setAllCorrectLetterInTheRightPlace(
      final String attempt, final List<WordElement> result) {
    Stream.iterate(0, p -> p + 1)
        .limit(attempt.length())
        .forEach(
            p -> {
              if (attempt.charAt(p) == this.targetWord.charAt(p)) {
                result.add(new WordElement(attempt.charAt(p), Result.CORRECT));
                lettersLeftToGuess.remove(Character.valueOf(attempt.charAt(p)));
              } else {
                result.add(new WordElement(attempt.charAt(p), Result.NOT_SET));
              }
            });
  }

  private void setAllRemainingLetterState(List<WordElement> result) {
    result.stream()
        .filter(e -> e.getResult() == Result.NOT_SET.getState())
        .forEach(
            e ->
                e.setResult(
                    this.lettersLeftToGuess.contains(e.getCharacter())
                        ? Result.CORRECT_WRONG_PLACE.getState()
                        : Result.WRONG.getState()));
  }
}
