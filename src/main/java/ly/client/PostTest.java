package ly.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

/**
 * This program demonstrates how to use the URLConnection class for a POST request.
 * @version 1.40 2016-04-24
 * @author Cay Horstmann
 */
public class PostTest
{
   public static void main(String[] args) throws IOException
   {
      String propsFilename = args.length > 0 ? args[0] : "post/post.properties"; 
      Properties props = new Properties();
      /*try (InputStream in = Files.newInputStream(Paths.get(propsFilename)))
      {
         props.load(in);
      }
      String urlString = props.remove("url").toString();
      Object userAgent = props.remove("User-Agent");
      Object redirects = props.remove("redirects");*/
      props.setProperty("url","http://127.0.0.1:8899/turing/a/" +
              "cio/missionOperateTrackHis/getProjectsGroup4Subject.json");

      String urlString = props.remove("url").toString();
      Object userAgent = props.remove("User-Agent");
      Object redirects = props.remove("redirects");

      String key1="masterId";
      String key2="filterType";
      String key3="distinctFlag";
      props.setProperty(key1,"bdaab2be717c42f7beed7a4b079802b8");
      props.setProperty(key2,"1");
      props.setProperty(key3,"1");
      CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
      String result = doPost(new URL(urlString), props, 
         userAgent == null ? null : userAgent.toString(), 
         redirects == null ? -1 : Integer.parseInt(redirects.toString()));
      System.out.println(result);
   }   

   /**
    * Do an HTTP POST.
    * @param url the URL to post to
    * @param nameValuePairs the query parameters
    * @param userAgent the user agent to use, or null for the default user agent
    * @param redirects the number of redirects to follow manually, or -1 for automatic redirects
    * @return the data returned from the server
    */
   public static String doPost(URL url, Map<Object, Object> nameValuePairs
           , String userAgent, int redirects)
         throws IOException
   {        
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      /*if (userAgent != null)
         connection.setRequestProperty("User-Agent", userAgent);*/

      // connection.setRequestMethod("POST");
      //connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
      //connection.setRequestProperty("Content-Type","application/json");
     /* if (redirects >= 0)
         connection.setInstanceFollowRedirects(false);*/
      
      connection.setDoOutput(true);
      OutputStream outputStream = connection.getOutputStream();
      try (PrintWriter out = new PrintWriter(outputStream))
      {
         boolean first = true;
         for (Map.Entry<Object, Object> pair : nameValuePairs.entrySet())
         {
            if (first) first = false;
            else out.print('&');
            String name = pair.getKey().toString();
            String value = pair.getValue().toString();
            out.print(name);
            out.print('=');
            out.print(URLEncoder.encode(value, "UTF-8"));
         }
      }

      StringBuilder response = new StringBuilder();
      try (Scanner in = new Scanner(connection.getInputStream(),
              "utf-8"))
      {
         while (in.hasNextLine())
         {
            response.append(in.nextLine());
            response.append("\n");
         }
      }
      catch (IOException e)
      {
         InputStream err = connection.getErrorStream();
         if (err == null) throw e;
         try (Scanner in = new Scanner(err))
         {
            response.append(in.nextLine());
            response.append("\n");
         }
      }

      return response.toString();
   }
}
