
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import java.util.Scanner;

public class LyTemp {
    Logger logger= LoggerFactory.getLogger(LyTemp.class);
    @Test
    public void getServerSocket() {
        try(Socket socket=new Socket();) {
            socket.connect(new InetSocketAddress(
                    "127.0.0.1",8189
            ),1000);
            InputStream inputStream = socket.getInputStream();
            byte[] bytes=new byte[inputStream.available()];
            inputStream.read(bytes);
            String s=new String(bytes, Charset.defaultCharset());
            logger.debug(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * byte测试
     */
    @Test
    public   void byteTest ( ){
        System.out.println((int)(char)(byte) -1);
    }
    @Test
    public void socket1() throws IOException {
        try(Socket s=new Socket("time-a.nist.gov",13)){
            s.setSoTimeout(1);
            InetAddress[] addresses=InetAddress.getAllByName(
                    "time-a.nist.gov");
            if(addresses.length>0){
                byte[] bytes=addresses[0].getAddress();
                for (int n=0;n<bytes.length;n++){
                    System.out.print((bytes[n]& 0xff)+(n!=bytes.length-1?".":""));
                }
                System.out.print(addresses[0].getHostAddress());
            }
            Scanner in=new Scanner(s.getInputStream(),
                    String.valueOf(StandardCharsets.UTF_8));
            while (in.hasNextLine()){
                String line=in.nextLine();
                System.out.println(line);
            }
            System.out.println("------------");
            Byte b=new Byte(((byte)129));
            System.out.print(b);
        }
    }
}
