package ldansorean.s5guessthecelebrity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String WEBPAGE_URL = "https://www.imdb.com/list/ls052283250/";

    private GameMaster gameMaster;
    private ImageDownloadTask imageDownloadTask;
    private ImageView image;
    private LinearLayout namesOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageDownloadTask = new ImageDownloadTask();
        image = findViewById(R.id.image);
        namesOptions = findViewById(R.id.namesOptions);

        initGameData();
        if (gameMaster.getNoOfCelebrities() == 0) {
            showErrorMessage("There was an error initializing the game. Check your internet connection and try again later.");
        } else {
            createChallenge();
        }

    }

    private void showErrorMessage(String message) {
        //show error message
        TextView errorLabel = findViewById(R.id.errorLabel);
        errorLabel.setText(message);
        errorLabel.setVisibility(View.VISIBLE);

        //hide everything else
        image.setVisibility(View.INVISIBLE);
        namesOptions.setVisibility(View.INVISIBLE);
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

    private void createChallenge() {
        try {
            Celebrity selectedCelebrity = gameMaster.getRandomCelebrity();
            Bitmap imageBitmap = imageDownloadTask.execute(selectedCelebrity.getPhotoUrl()).get();
            if (imageBitmap != null)
                image.setImageBitmap(imageBitmap);
            else
                showErrorMessage("There was an error getting the celebrity image. Check your internet connection and try again later.");
        } catch (Exception e) {
            e.printStackTrace();
            showErrorMessage("There was an error getting the celebrity image. Check your internet connection and try again later.");
        }
    }
}
