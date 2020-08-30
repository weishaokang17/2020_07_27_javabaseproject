package per.wsk.java.study03;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author weishaokang
 * @date 2020-08-28 23:01
 * @project 2020_07_27_javabaseproject
 * @description:
 *
 *
 * 2.客户端给服务端发送文本，服务端会将文本转成大写在返回给客户端。
 */
public class TCPExer02 {

    @Test
    public void test01() throws IOException {
        Socket socket = new Socket("127.0.0.1", 4004);
        OutputStream outputStream = socket.getOutputStream();

        outputStream.write("abcdefiyhopq".getBytes("UTF-8"));

        socket.shutdownOutput();

        InputStream inputStream = socket.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream,"UTF-8");

        StringBuilder sb = new StringBuilder();
        char[] arr = new char[10];
        int length;
        while ((length = reader.read(arr)) != -1){
            sb.append(arr,0,length);
        }
        System.out.println(sb);// 输出：  ABCDEFIYHOPQ

        reader.close();
        outputStream.close();
        socket.close();

    }

    @Test
    public void test02() throws IOException {
        ServerSocket serverSocket = new ServerSocket(4004);
        Socket accept = serverSocket.accept();

        InputStream inputStream = accept.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream,"UTF-8");

        StringBuilder sb = new StringBuilder();
        char[] arr = new char[10];
        int length;
        while ((length = reader.read(arr)) != -1){
            sb.append(arr,0,length);
        }
        String result = sb.toString().toUpperCase();

        OutputStream outputStream = accept.getOutputStream();
        outputStream.write(result.getBytes("UTF-8"));

        outputStream.close();
        reader.close();
        accept.close();
        serverSocket.close();

    }

}
