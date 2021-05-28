package ly.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MainLy {
    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8189);

            Socket accept = serverSocket.accept(); //一旦有人访问则返回该对象

            InputStream inputStream = accept.getInputStream();
            //去掉Scanner获取会导致cmd telnet收不到输出(猜测是因为太快?
            // 因为SocketClient.class客户端可以收到)
            try (Scanner in = new Scanner(inputStream, String.valueOf(StandardCharsets.UTF_8))) {

                OutputStream outputStream = accept.getOutputStream();//服务端输出流的信息会成为客户端程序的输入
                boolean done=false;
                outputStream.write(("----pleaseSaySomething-----\n").getBytes());
                outputStream.flush();
                while(!done&&in.hasNextLine()){
                    String s=in.nextLine();
                    if("bye".equals(s)){
                        done=true;
                    }else {
                        outputStream.write(("a test ["+s+"]").getBytes());
                        outputStream.flush();
                    }
                }
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
