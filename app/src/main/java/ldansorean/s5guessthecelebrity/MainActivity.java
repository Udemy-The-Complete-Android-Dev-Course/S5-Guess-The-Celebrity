package ldansorean.s5guessthecelebrity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String WEBPAGE_URL = "https://www.imdb.com/list/ls052283250/";

    private GameMaster gameMaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initGameData();
        if (gameMaster.getNoOfCelebrities() == 0) {
            TextView errorLabel = findViewById(R.id.errorLabel);
            errorLabel.setText("There was an error initializing the game. Check your internet connection and try again later.");
            errorLabel.setVisibility(View.VISIBLE);
        } else {
            TextView errorLabel = findViewById(R.id.errorLabel);
            errorLabel.setText("Downloaded " + gameMaster.getNoOfCelebrities() + " celebrities infos.");
            errorLabel.setVisibility(View.VISIBLE);
        }

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
}
