package wazzle.model.minigame;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import wazzle.model.minigame.MiniGame.State;
import wazzle.model.minigame.attempt.MiniGameWord;

public class TestMiniGame {

    MiniGame currentMinigame;

    @Test
    public void testSavingAndLoadingMiniGame() {

        String targetWord = "pasta";
        currentMinigame = new MiniGameImpl(targetWord);
        SavedMiniGame savedMiniGame = currentMinigame.takeMiniGameSnapshot();
        currentMinigame.loadMiniGame(savedMiniGame);
//        assertEquals(targetWord, savedMiniGame.getSavedTargetWord());
        assertEquals(Collections.EMPTY_LIST, savedMiniGame.getSavedMiniGameWordsSoFar());
        assertEquals(State.IN_PROGRESS, currentMinigame.getGameState());


        List<MiniGameWord> testGuessedWordsSoFar = new ArrayList<>();
        String wrongGuessedWord = "festa";
        testGuessedWordsSoFar.add(currentMinigame.computeResult(wrongGuessedWord));
        savedMiniGame = currentMinigame.takeMiniGameSnapshot();
        currentMinigame.loadMiniGame(savedMiniGame);
        assertEquals(targetWord, savedMiniGame.getSavedTargetWord());
        assertEquals(testGuessedWordsSoFar, savedMiniGame.getSavedMiniGameWordsSoFar());
        assertEquals(State.IN_PROGRESS, currentMinigame.getGameState());
        
        String rightGuessedWord = "pasta";
        savedMiniGame = currentMinigame.takeMiniGameSnapshot();
        currentMinigame.loadMiniGame(savedMiniGame);
        testGuessedWordsSoFar.add(currentMinigame.computeResult(rightGuessedWord));
        assertEquals(targetWord, savedMiniGame.getSavedTargetWord());
        assertEquals(testGuessedWordsSoFar, savedMiniGame.getSavedMiniGameWordsSoFar());
        assertEquals(State.WON, currentMinigame.getGameState());

    }
}
