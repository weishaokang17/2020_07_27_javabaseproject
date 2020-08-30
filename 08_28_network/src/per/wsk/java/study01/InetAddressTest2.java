package per.wsk.java.study01;

/**
 * @Author weishaokang
 * @date 2020-08-28 17:39
 * @project 2020_07_27_javabaseproject
 * @description:
 *
 * 一、网络传输协议：
 *  计算机网络通信涉及内容很多，涉及很多层，网络通信很复杂。在制定协议时，
 *  把复杂成份分解成一些简单的成份，再将它们复合起来。最常用的复合方式是层次方式，
 *  即同层间可以通信、上一层可以调用下一层，而与
 *  再下一层不发生关系。各层互不影响，利于系统的开发和扩展。
 *
 * 二、
 * 传输层协议中有两个非常重要的协议：
 *  传输控制协议TCP(Transmission Control Protocol)
 *  用户数据报协议UDP(User Datagram Protocol)。
 * IP(Internet Protocol)协议是网络层的主要协议，支持网间互连的数据通信。
 *
 *  即传输层有两个重要的协议，TCP协议和UDP协议，网络层的主要协议是IP协议。
 *
 * 三、
 *  TCP协议：
 *  使用TCP协议前，须先建立TCP连接，形成传输数据通道
 *  传输前，采用“三次握手”方式，点对点通信，是可靠的
 *  TCP协议进行通信的两个应用进程：客户端、服务端。
 *  在连接中可进行大数据量的传输
 *  传输完毕，需释放已建立的连接，效率低
 *  UDP协议：
 *  将数据、源、目的封装成数据包，不需要建立连接
 *  每个数据报的大小限制在64K内
 *  发送不管对方是否准备好，接收方收到也不确认，故是不可靠的
 *  可以广播发送
 *  发送数据结束时无需释放资源，开销小，速度快
 *
 * 四、
    端口号与IP地址的组合得出一个网络套接字：Socket
 *  也就是说，根据一个Socket能确定某台计算机的某个进程，网络通信实质上就是多台计算机的进程之间的
 *  通信，所以网络通信也称为Socket通信。
 *
 *   Socket允许程序把网络连接当成一个流，数据在两个Socket间通过IO传输。
 *   一般主动发起通信的应用程序属客户端，等待通信请求的为服务端。
 *
 *
 */
public class InetAddressTest2 {
    /*
    Socket类：

     Socket类的常用构造器：
     public Socket(InetAddress address,int port)创建一个流套接字并将其连接到指定
        IP 地址的指定端口号。
     public Socket(String host,int port)创建一个流套接字并将其连接到指定主机
        上的指定端口号。

     Socket类的常用方法：
     public InputStream getInputStream()返回此套接字的输入流。可以用于接收网络消息
     public OutputStream getOutputStream()返回此套接字的输出流。可以用于发送网络消息
     public InetAddress getInetAddress()此套接字连接到的远程 IP 地址；
        如果套接字是未连接的，则返回 null。
     public InetAddress getLocalAddress()获取套接字绑定的本地地址。 即本端的IP地址
     public int getPort()此套接字连接到的远程端口号；如果尚未连接套接字，则返回 0。
     public int getLocalPort()返回此套接字绑定到的本地端口。
        如果尚未绑定套接字，则返回 -1。即本端的端口号。
     public void close()关闭此套接字。套接字被关闭后，便不可在以后的网络连接中使用
    （即无法重新连接或重新绑定）。需要创建新的套接字对象。 关闭此套接字也将会关闭
      该套接字的 InputStream 和OutputStream。
     public void shutdownInput()如果在套接字上调用 shutdownInput() 后从
        套接字输入流读取内容，则流将返回 EOF（文件结束符）。
        即不能在从此套接字的输入流中接收任何数据。
        也就是说，在调用shutdownInput()方法后就不能再从套接字输入流读取数据了。

     public void shutdownOutput()禁用此套接字的输出流。对于 TCP 套接字，
        任何以前写入的数据都将被发送，并且后跟 TCP 的正常连接终止序列。
        如果在套接字上调用 shutdownOutput() 后写入套接字输出流，
        则该流将抛出 IOException。 即不能通过此套接字的输出流发送任何数据。
        也就是说，在调用shutdownInput()方法后就不能再从套接字输出流写入数据了。
     */
}
