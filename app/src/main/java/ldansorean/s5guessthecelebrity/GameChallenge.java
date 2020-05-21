package ldansorean.s5guessthecelebrity;

import java.util.List;

public class GameChallenge {
    private Celebrity winningCelebrity;
    private List<String> nameOptions;
    private int correctIndex;

    public GameChallenge(Celebrity winningCelebrity, List<String> nameOptions, int correctIndex) {
        this.winningCelebrity = winningCelebrity;
        this.nameOptions = nameOptions;
        this.correctIndex = correctIndex;
    }

    public List<String> getNameOptions() {
        return nameOptions;
    }

    public String getImageURL() {
        return winningCelebrity.getPhotoUrl();
    }

    public String getCelebrityName() {
        return winningCelebrity.getName();
    }

    public boolean isGuessCorrect(int guessIndex) {
        return correctIndex == guessIndex;
    }
}
