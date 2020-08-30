package per.wsk.java.study03;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author weishaokang
 * @date 2020-08-28 22:48
 * @project 2020_07_27_javabaseproject
 * @description:
 *
 * 服务端读取图片并发送给客户端，客户端保存图片到本地
 */
public class TCPExer01 {

    @Test
    public void test01() throws IOException {
        Socket socket = new Socket("127.0.0.1",417);

        socket.shutdownOutput();

        InputStream inputStream = socket.getInputStream();
        FileOutputStream outputStream = new FileOutputStream("beauty4.jpg");
        byte[] arr = new byte[100];
        int length;
        while ((length = inputStream.read(arr)) != -1){
            outputStream.write(arr,0,length);
        }

        outputStream.close();
        inputStream.close();
        socket.close();

    }


    @Test
    public void test02() throws IOException {
        ServerSocket serverSocket = new ServerSocket(417);

        Socket accept = serverSocket.accept();

        OutputStream outputStream = accept.getOutputStream();
        FileInputStream inputStream = new FileInputStream("beauty.jpg");
        byte[] arr = new byte[100];
        int length;
        while ((length = inputStream.read(arr)) != -1){
            outputStream.write(arr,0,length);
        }

        inputStream.close();
        outputStream.close();
        accept.close();
        serverSocket.close();


    }


}
