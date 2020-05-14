package ldansorean.s5guessthecelebrity;

import android.os.AsyncTask;

import java.io.InputStream;
import java.net.URL;

public class WebpageDownloadTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... urls) {
        try {
            InputStream is = new URL(urls[0]).openConnection().getInputStream();
            StringBuilder webPageContent = new StringBuilder();
            int c = is.read();
            while (c != -1) {
                webPageContent.append((char) c);
                c = is.read();
            }
            return webPageContent.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
