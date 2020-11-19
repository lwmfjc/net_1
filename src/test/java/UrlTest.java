import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UrlTest {
    @Test
    public void hello() throws MalformedURLException {
        URL url=new URL("http://127.0.0.1:8899/turing/a/cio/missionOperateTrackHis/getProjectsGroup4Subject.json");

        try {
            InputStream inputStream = url.openStream();
            InputStreamReader inputStreamReader=new InputStreamReader(
                    inputStream
            );
            System.out.print(inputStreamReader.toString());
            //URLConnection urlConnection = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
