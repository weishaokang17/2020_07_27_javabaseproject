package per.wsk.java.study02;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @Author weishaokang
 * @date 2020-08-28 21:06
 * @project 2020_07_27_javabaseproject
 * @description:
 *
 * 自己的练习
 */
public class TCPTestOwn01 {


    @Test
    public void test01() throws IOException {
        Socket socket = new Socket("127.0.0.1", 4444);
        OutputStream outputStream = socket.getOutputStream();
        String requestParams = "客户端的请求";
        outputStream.write(requestParams.getBytes());

        outputStream.close();
        socket.close();
    }

    @Test
    public void test02() throws IOException {
        ServerSocket server = new ServerSocket(4444);
        Socket accept = server.accept();

        InputStream inputStream = accept.getInputStream();

        //转换流
        InputStreamReader reader = new InputStreamReader(inputStream);

        char[] arr = new char[10];
        StringBuilder sb = new StringBuilder();
        int readLength;
        while ((readLength = reader.read(arr)) != -1){
            sb.append(arr,0,readLength);
        }

        System.out.println(sb);//输出：  客户端的请求

        reader.close();
        accept.close();
        server.close();
    }

}
