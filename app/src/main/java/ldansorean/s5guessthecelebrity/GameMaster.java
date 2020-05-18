package ldansorean.s5guessthecelebrity;

import java.util.ArrayList;
import java.util.LinkedHashSet;
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

    public GameChallenge getGameChallenge() {
        Celebrity winningCelebrity = getRandomCelebrity();
        int celebrityIndex = randomizer.nextInt(NO_OF_NAME_OPTIONS);
        ArrayList<String> nameOptions = new ArrayList<>();

        int idx = 0;
        while (idx < 4) {
            if (idx == celebrityIndex) {
                nameOptions.add(winningCelebrity.getName());
                idx++;
            } else {
                String randomName = getRandomCelebrity().getName();
                if (!nameOptions.contains(randomName)) {
                    nameOptions.add(randomName);
                    idx++;
                }
            }
        }

        return new GameChallenge(winningCelebrity, nameOptions, celebrityIndex);
    }

    private Celebrity getRandomCelebrity() {
        return celebrities.get(randomizer.nextInt(celebrities.size()));
    }
}
