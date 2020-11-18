package ly;

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
            try (Scanner in = new Scanner(inputStream, String.valueOf(StandardCharsets.UTF_8))) {

                OutputStream outputStream = accept.getOutputStream();//服务端输出流的信息会称为客户端程序的输入
                outputStream.write("a test .".getBytes());
                outputStream.flush();
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
