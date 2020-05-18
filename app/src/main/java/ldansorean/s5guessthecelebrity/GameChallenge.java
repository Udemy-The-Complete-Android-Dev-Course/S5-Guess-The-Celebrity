package ldansorean.s5guessthecelebrity;

import java.util.ArrayList;

public class GameChallenge {

    private Celebrity winningCelebrity;
    private ArrayList<String> nameOptions;
    private int correctIndex;

    public GameChallenge(Celebrity winningCelebrity, ArrayList<String> nameOptions, int correctIndex) {
        this.winningCelebrity = winningCelebrity;
        this.nameOptions = nameOptions;
        this.correctIndex = correctIndex;
    }

    public ArrayList<String> getNameOptions() {
        return nameOptions;
    }

    public String getImageURL() {
        return winningCelebrity.getPhotoUrl();
    }

    public boolean isGuessCorrect(int guessIndex) {
        return correctIndex == guessIndex;
    }
}
