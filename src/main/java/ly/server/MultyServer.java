package ly.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MultyServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(9888)) {
            int i=0;
            while (true) {//循环检测是否有连接
                Socket incomming = serverSocket.accept();
                //当检测到连接时开启一个线程进行接收
                Runnable runnable = new MyThread(incomming,i);
                Thread t = new Thread(runnable);
                t.start();
                i++;
            }

        } catch (Exception e) {

        }
    }

}

class MyThread implements Runnable {
    private Socket incomming = null;
    private int i;//次数

    public MyThread(Socket socket,int i) {
        this.incomming = socket;
        this.i=i;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = incomming.getInputStream();
            OutputStream outputStream = incomming.getOutputStream();


            Scanner in = new Scanner(inputStream,"GBK");
            PrintWriter printWriter =
                    new PrintWriter(new OutputStreamWriter(outputStream,"GBK")
                            ,true);
            System.out.println("-0000你连接上了小熊底"+this.i+"]~输出exit退出");

            printWriter.println("你是第"+this.i+"个连接上了小熊底"+"输出exit退出");
            String s;
            boolean done = false;
            while (!done&&in.hasNextLine()) {
                s = in.nextLine();
                printWriter.println("你输入了:[" + s + "]");
                if ("exit".equals(s)) {
                    //printWriter.println("即将退出---->");
                    done = true;
                }


            }
            outputStream.close();
            inputStream.close(); //这个要记得关闭
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
