package per.wsk.java.study02;

import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author weishaokang
 * @date 2020-08-28 22:24
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class TCPTestOwn03 {

    @Test
    public void test01() throws IOException {
        Socket socket = new Socket("127.0.0.1",7779);
        OutputStream outputStream = socket.getOutputStream();

        FileInputStream inputStream = new FileInputStream("beauty.jpg");
        byte[] arr = new byte[10];
        int length;
        while ((length = inputStream.read(arr)) != -1){
            outputStream.write(arr,0,length);
        }

        socket.shutdownOutput();

        StringBuilder sb = new StringBuilder();
        InputStream inputStream1 = socket.getInputStream();
        //转换流
        InputStreamReader reader = new InputStreamReader(inputStream1);
        char[] chars = new char[10];
        int readLength;
        while ((readLength = reader.read(chars)) != -1){
            sb.append(chars,0,readLength);
        }
        System.out.println(sb);


        reader.close();
        inputStream.close();
        outputStream.close();
        socket.close();

    }

    @Test
    public void test02() throws IOException {
        ServerSocket serverSocket = new ServerSocket(7779);
        Socket accept = serverSocket.accept();

        InputStream inputStream = accept.getInputStream();

        FileOutputStream outputStream = new FileOutputStream("beauty3.jpg");

        byte[] arr = new byte[100];
        int readLength;
        while ((readLength = inputStream.read(arr)) != -1){
            outputStream.write(arr,0,readLength);
        }

        OutputStream outputStream1 = accept.getOutputStream();
        outputStream1.write("服务器已收到照片".getBytes());


        outputStream.close();
        outputStream1.close();
        inputStream.close();
        accept.close();
        serverSocket.close();
    }


    @Test
    public void test03(){
        File file = new File("beauty3.jpg");
        file.delete();
    }
}
