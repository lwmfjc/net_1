import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.IOUtils;
import ly.entity.CountObj;
import ly.entity.HttpObj;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class UrlTest {
    @Test
    public void hello() throws MalformedURLException {
        URL url=new URL("http://192.168.0.27:7003//turing/a/cio/missionOperateTrackHis/getProjectDeclarationCount.json");

        try {
            InputStream inputStream = url.openStream();
             Scanner in=new Scanner(inputStream,"UTF-8");
            StringBuilder stringBuilder=new StringBuilder();
             while (in.hasNextLine()){
                 stringBuilder.append(in.nextLine());
                // System.out.println(stringBuilder);
             }
           // JSONObject jsonObject = JSONObject.parseObject(stringBuilder.toString());
            // System.out.println(jsonObject.get("obj"));
            HttpObj httpObj = JSONObject.parseObject(stringBuilder.toString(), HttpObj.class);
             System.out.println(httpObj.getObj().getAllCount());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
