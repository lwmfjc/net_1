import org.junit.Test;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class UrlTest {
    @Test
    public void write2Url(){
        try {
            URL url=new URL("http://127.0.0.1:8899/turing/a/" +
                    "cio/missionOperateTrackHis/getProjectsGroup4Subject.json");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            //urlConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            OutputStream outputStream = urlConnection.getOutputStream();
            //要发送文本消息，使用PrintWriter
            try(PrintWriter out=new PrintWriter(
                    outputStream
            )) {
                String key1 = "masterId";
                String key2 = "filterType";
                String key3 = "distinctFlag";
                out.print(key1 + "=" + URLEncoder.encode("bdaab2be717c42f7beed7a4b079802b8",
                        "UTF-8") + "&");
                out.print(key2 + "=" + URLEncoder.encode("1", "UTF-8") + "&");
                out.print(key3 + "=" + URLEncoder.encode("1", "UTF-8"));
                // TODO: 2020/11/20 这里不知道出的什么问题,参数无法成功传给后台
                //outputStream.close();
                out.close(); //这里如果没有关闭，则参数不会发送过来
                InputStream inputStream = urlConnection.getInputStream();
                try {
                    Scanner scanner = new Scanner(inputStream, "UTF-8");
                    StringBuilder sb = new StringBuilder();
                    while (scanner.hasNextLine()) {
                        sb.append(scanner.nextLine());
                    }
                    System.out.println(sb);
                }catch (Exception e){

                }
            }

        } catch ( Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getUrlInfo(){
        try {
            URL url=new URL("http://localhost:8899/turing/a" +
                    "/cio/missionOperateTrackHis/getProjectDeclarationCount.json");
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            //查询的时候才会开始与服务器通信
            System.out.println(urlConnection.getContentType());
            Object content = urlConnection.getContent();
            System.out.println(urlConnection.getContent());
            System.out.println("--------------");
            urlConnection.setDoOutput(true);//用于向Web服务器提交数据

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void hello() throws MalformedURLException {
        URL url=new URL("http://localhost:8899/turing/a/cio/missionOperateTrackHis/getProjectDeclarationCount.json");

        try {
            //在openStream之后才向服务器发起请求
            InputStream inputStream = url.openStream();

            //用Scanner接收输入
             Scanner in=new Scanner(inputStream,"UTF-8");
            StringBuilder stringBuilder=new StringBuilder();
             while (in.hasNextLine()){
                 stringBuilder.append(in.nextLine());
                // System.out.println(stringBuilder);
             }
           // JSONObject jsonObject = JSONObject.parseObject(stringBuilder.toString());
            // System.out.println(jsonObject.get("obj"));
            /*HttpObj httpObj = JSONObject.parseObject(stringBuilder.toString(), HttpObj.class);
             System.out.println(httpObj.getObj().getAllCount());*/
            //System.out.println(stringBuilder);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
