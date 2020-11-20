package ly.client;

import jdk.nashorn.internal.ir.debug.JSONWriter;
import jdk.nashorn.internal.parser.JSONParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SocketClient {
    public static void main(String[] args) {
        try(Socket socket=new Socket() ) {
            socket.connect(new InetSocketAddress(
                    "127.0.0.1",9888
            ),1000);
            //Thread.sleep(1000);
            InputStream inputStream = socket.getInputStream();

            /*InputStreamReader in=new InputStreamReader(inputStream, "GBK");

            int read = in.read();
            while (read!=-1){
                System.out.print((char)read);
                read=in.read();
            }*/
            int read = inputStream.read();
            if(read!=-1){
                int available = inputStream.available();
                byte[] bytes2=new byte[1];
                if(available>0) {
                    byte[] bytes= new byte[available];
                    inputStream.read(bytes);
                    bytes2 = new byte[available + 1];
                    for (int n = 0; n < available; n++) {
                        bytes2[n + 1] = bytes[n];
                    }
                }
                bytes2[0] = (byte) read;
                String s = new String(bytes2, "GBK");
                System.out.println(s);
            }


            /*int read = inputStream.read();
            while (read!=-1){
                System.out.println( (char)read);
                read = inputStream.read();
            }*/
            //byte[] bytes=new byte[inputStream.available()];
            //inputStream.read(bytes);
            //String s=new String(bytes, Charset.defaultCharset());
           //System.out.println(s+"abc");
//            Scanner s2=new Scanner(System.in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
