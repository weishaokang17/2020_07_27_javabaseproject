package per.wsk.java.study02;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现TCP的网络编程
 * 例子1：客户端发送信息给服务端，服务端将数据显示在控制台上
 *
 * @author shkstart
 * @create 2019 下午 3:30
 */
public class TCPTest1 {

    /*
     客户端Socket的工作过程包含以下四个基本的步骤：
    ① 创建 Socket：根据指定服务端的 IP 地址或端口号构造 Socket 类对象。
     创建的同时会自动向服务器方发起连接。若服务器端
     响应，则建立客户端到服务器的通信线路。若连接失败，会出现异常。
    ② 打开连接到 Socket 的输入/出流： 使用 getInputStream()方法获得输入流，使用
    getOutputStream()方法获得输出流，进行数据传输
    ③ 按照一定的协议对 Socket 进行读/写操作：通过输入流读取服务器放入线路的信息
    （但不能读取自己放入线路的信息），通过输出流将信息写入线程。
    ④ 关闭 Socket：断开客户端到服务器的连接，释放线路
     */


    //因TCP协议进行数据传输前要三次握手，所以要先启动服务端，再启用客户端
    //客户端
    @Test
    public void client()  {
        Socket socket = null;
        OutputStream os = null;
        try {
            //1.创建Socket对象，指明服务器端的ip和端口号
            InetAddress inet = InetAddress.getByName("192.168.14.100");
            socket = new Socket(inet,8899);
            //2.获取一个输出流，用于输出数据
            os = socket.getOutputStream();
            //3.写出数据的操作
            os.write("你好，我是客户端mm".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.资源的关闭
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    /*
    服务器程序的工作过程包含以下四个基本的步骤：
    调用 ServerSocket(int port) ：创建一个服务器端套接字，并绑定到指定端口
    上。用于监听客户端的请求。
    调用 accept()：监听连接请求，如果客户端请求连接，则接受连接，返回通信
    套接字对象。
    调用 该Socket类对象的 getOutputStream() 和 getInputStream ()：获取输出
    流和输入流，开始网络数据的发送和接收。
    关闭ServerSocket和Socket对象：客户端访问结束，关闭通信套接字。

     */
    //服务端
    @Test
    public void server()  {

        ServerSocket ss = null;
        Socket socket = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            //1.创建服务器端的ServerSocket，指明自己的端口号
            ss = new ServerSocket(8899);
            //2.调用accept()表示接收来自于客户端的socket
            socket = ss.accept();
            //3.获取输入流
            is = socket.getInputStream();

            //不建议这样写，可能会有乱码
//        byte[] buffer = new byte[1024];
//        int len;
//        while((len = is.read(buffer)) != -1){
//            String str = new String(buffer,0,len);
//            System.out.print(str);
//        }
            //4.读取输入流中的数据
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[5];
            int len;
            while((len = is.read(buffer)) != -1){
                baos.write(buffer,0,len);
            }

            System.out.println(baos.toString());

            System.out.println("收到了来自于：" + socket.getInetAddress().getHostAddress() + "的数据");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(baos != null){
                //5.关闭资源
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(ss != null){
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }





    }

}
