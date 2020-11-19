package ly.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;

public class SocketClient {
    public static void main(String[] args) {
        try(Socket socket=new Socket() ) {
            socket.connect(new InetSocketAddress(
                    "127.0.0.1",8190
            ),1000);
            //Thread.sleep(1000);
            InputStream inputStream = socket.getInputStream();
            int read = inputStream.read();
            while (read!=-1){
                System.out.println( read);
                read = inputStream.read();
            }
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
