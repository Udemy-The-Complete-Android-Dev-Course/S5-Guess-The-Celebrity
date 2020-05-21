package ldansorean.s5guessthecelebrity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String WEBPAGE_URL = "https://www.imdb.com/list/ls052283250/";

    private GameMaster gameMaster;
    private GameChallenge gameChallenge;
    private ImageView image;
    private LinearLayout namesOptionButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.image);
        namesOptionButtons = findViewById(R.id.namesOptions);

        initGameData();
        if (gameMaster.getNoOfCelebrities() == 0) {
            showErrorMessage("There was an error initializing the game. Check your internet connection and try again later.");
        } else {
            showNewChallenge();
        }

    }

    public void celebrityChosen(View view) {
        int idx = namesOptionButtons.indexOfChild(view);
        if (gameChallenge.isGuessCorrect(idx)) {
            Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Wrong! It was " + gameChallenge.getCelebrityName(), Toast.LENGTH_SHORT).show();
        }

        showNewChallenge();
    }

    private void showErrorMessage(String message) {
        //show error message
        TextView errorLabel = findViewById(R.id.errorLabel);
        errorLabel.setText(message);
        errorLabel.setVisibility(View.VISIBLE);

        //hide everything else
        image.setVisibility(View.INVISIBLE);
        namesOptionButtons.setVisibility(View.INVISIBLE);
    }

    private void initGameData() {
        WebpageDownloadTask webpageDownloadTask = new WebpageDownloadTask();
        try {
            String webPageContent = webpageDownloadTask.execute(WEBPAGE_URL).get();
            ArrayList<Celebrity> celebrities = new DataExtractionService().getCelebrities(webPageContent);
            gameMaster = new GameMaster(celebrities);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showNewChallenge() {
        try {
            gameChallenge = gameMaster.createGameChallenge();
            //set image
            Bitmap imageBitmap = new ImageDownloadTask().execute(gameChallenge.getImageURL()).get();
            if (imageBitmap != null)
                image.setImageBitmap(imageBitmap);
            else
                showErrorMessage("There was an error getting the celebrity image. Check your internet connection and try again later.");

            //set names
            for (int i = 0; i < namesOptionButtons.getChildCount(); i++) {
                Button button = (Button) namesOptionButtons.getChildAt(i);
                button.setText(gameChallenge.getNameOptions().get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
            showErrorMessage("There was an error getting the celebrity image. Check your internet connection and try again later.");
        }
    }
}
