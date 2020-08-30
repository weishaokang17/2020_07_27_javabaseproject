package per.wsk.java.study02;

import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author weishaokang
 * @date 2020-08-28 21:20
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class TCPTest0wn02 {

    @Test
    public void test01() throws IOException {
        Socket socket = new Socket("127.0.0.1", 7997);
        OutputStream outputStream = socket.getOutputStream();

        FileInputStream inputStream = new FileInputStream("beauty.jpg");
        byte[] arr = new byte[100];
        int readLength;
        while ((readLength = inputStream.read(arr)) != -1){
            outputStream.write(arr,0,readLength);
        }

        inputStream.close();
        outputStream.close();
        socket.close();
    }

    @Test
    public void test02() throws IOException {
        ServerSocket server = new ServerSocket(7997);
        Socket accept = server.accept();

        InputStream inputStream = accept.getInputStream();
        FileOutputStream outputStream = new FileOutputStream("beauty2.jpg");

        byte[] arr = new byte[100];
        int length;
        while ((length = inputStream.read(arr)) != -1){
            outputStream.write(arr,0,length);
        }

        inputStream.close();
        outputStream.close();
        accept.close();
        server.close();
    }

}
