package ldansorean.s5guessthecelebrity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;

public class GameMaster {
    private static final int NO_OF_NAME_OPTIONS = 4;
    private ArrayList<Celebrity> celebrities;
    private Random randomizer;

    public GameMaster(ArrayList<Celebrity> celebrities) {
        if (celebrities == null) {
            this.celebrities = new ArrayList<>();
        } else {
            this.celebrities = celebrities;
        }
        randomizer = new Random();
    }

    public int getNoOfCelebrities() {
        return celebrities.size();
    }

    protected GameChallenge getGameChallenge() {
        //convoluted way of creating an array list of a fixed size initialized with null
        ArrayList<String> nameOptions = new ArrayList<>(Arrays.asList(new String[4]));

        Celebrity celebrity = getRandomCelebrity();
        int celebrityIndex = randomizer.nextInt(NO_OF_NAME_OPTIONS);
        nameOptions.set(celebrityIndex, celebrity.getName());

        int idx = 0;
        while (idx < 4) {
            if (idx == celebrityIndex) {
                idx++;
            } else {
                String randomName = getRandomCelebrity().getName();
                if (!nameOptions.contains(randomName)) {
                    nameOptions.set(idx, randomName);
                    idx++;
                }
            }
        }

        return new GameChallenge(celebrity, nameOptions, celebrityIndex);
    }

    private Celebrity getRandomCelebrity() {
        return celebrities.get(randomizer.nextInt(celebrities.size()));
    }

    protected class GameChallenge {

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

        public boolean isGuessCorrect(int guessIndex) {
            return correctIndex == guessIndex;
        }
    }
}
