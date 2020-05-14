package ldansorean.s5guessthecelebrity;

import java.util.ArrayList;
import java.util.Random;

public class GameMaster {

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

    public Celebrity getRandomCelebrity() {
        return celebrities.get(randomizer.nextInt(celebrities.size()));
    }
}
