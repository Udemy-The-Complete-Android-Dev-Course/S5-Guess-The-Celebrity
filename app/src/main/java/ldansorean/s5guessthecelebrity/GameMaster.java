package ldansorean.s5guessthecelebrity;

import java.util.ArrayList;
import java.util.Arrays;
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

    public GameChallenge createGameChallenge() {
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
}
