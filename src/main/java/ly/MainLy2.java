package ly;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MainLy2 {
    public static void main(String[] args) throws IOException {
        System.out.println("Test");
        try (ServerSocket s = new ServerSocket(8190)) {
            //等待客户端连接
            try (Socket incoming = s.accept()) {
                InputStream inputStream = incoming.getInputStream();

                //使用scanner获取客户端传来的信息
                try (Scanner in = new Scanner(inputStream, String.valueOf(StandardCharsets.UTF_8))) {
                    OutputStream outputStream = incoming.getOutputStream();
                    PrintWriter out = new PrintWriter(
                            new OutputStreamWriter(outputStream, "UTF-8"), true
                    );
                    // System.out.print("啦啦啦啦");
                    outputStream.write("呵呵".getBytes());
                    outputStream.flush();
                    outputStream.close();
                    out.println("Hello !Enter BYE to exist.");
                    //out.close();
                    //outputStream.close();
                    /*boolean done=false;
                    while (!done&&in.hasNextLine()){
                        String line=in.nextLine();
                        out.println("Echo: "+line);
                        if(line.trim().equals("BYE")) done=true;
                    }*/
                }
            }

        }
    }
}
