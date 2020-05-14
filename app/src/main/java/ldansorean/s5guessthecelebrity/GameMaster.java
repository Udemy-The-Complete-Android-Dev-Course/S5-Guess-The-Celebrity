package ldansorean.s5guessthecelebrity;

import java.util.ArrayList;

public class GameMaster {

    private ArrayList<Celebrity> celebrities;

    public GameMaster(ArrayList<Celebrity> celebrities) {
        if (celebrities == null) {
            this.celebrities = new ArrayList<>();
        } else {
            this.celebrities = celebrities;
        }
    }

    public int getNoOfCelebrities() {
        return celebrities.size();
    }
}
