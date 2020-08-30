package per.wsk.java.study01;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 一、网络编程中有两个主要的问题：
 * 1.如何准确地定位网络上一台或多台主机；定位主机上的特定的应用
 * 2.找到主机后如何可靠高效地进行数据传输
 *
 * 二、网络编程中的两个要素：
 * 1.对应问题一：IP和端口号
 * 2.对应问题二：提供网络通信协议：TCP/IP参考模型（应用层、传输层、网络层、物理+数据链路层）
 *
 *
 * 三、通信要素一：IP和端口号
 *
 * 1. IP:唯一的标识 Internet 上的计算机（通信实体）
 * 2. 在Java中使用InetAddress类代表IP
 * 3. IP分类：IPv4 和 IPv6 ; 万维网 和 局域网
 *      ①IP地址分类方式1：IPV4 和 IPV6
 *          IPV4：4个字节组成，4个0-255。大概42亿(即256的4次方)，30亿都在北美，亚洲4亿。2011年初已
 *              经用尽。4个0-255直接用.分开，如124.274.20.14
 *          IPV6：128位（16个字节），写成8个无符号整数，每个整数用四个十六进制位表示，
 *              8个无符号整数之间用冒号（：）分开，如：3ffe:3201:1401:1280:c8ff:fe4d:db39:1984
 *
 *      ②IP地址分类方式2：公网地址(万维网使用)和私有地址(局域网使用)。192.168.
 *      开头的就是私有址址，范围即为192.168.0.0--192.168.255.255，专门为组织机
 *      构内部使用
 *
 *     IP有一个明显的缺点，不易记忆，例如要访问百度、淘宝等，都要记住这些网站的IP才能再根据IP访问。
 *
 * 4. 域名:   www.baidu.com   www.mi.com（小米的域名）  www.sina.com（新浪的域名）
 *            www.jd.com（京东的域名）
 *            www.vip.com（这个域名是唯品会的）
 *      通过域名来访问想要访问的网站，域名就比IP容易记多了。
 *      在网址上输入 要访问的网站的域名后，先找本机hosts，目录是C:\Windows\System32\drivers\etc\hosts，
 *      是否有输入的域名地址，没有的话，再通过DNS，DNS是域名解析服务器，获取实际IP。
 *      再访问实际IP对应的网站。
 *
 * 5. 本地IP地址：127.0.0.1   本地IP对应的域名是 localhost
 *
 * 6.端口号：正在计算机上运行的进程。
 *    ①一个运行程序在计算机中可能有一个或多个进程
 *    ② 不同的进程有不同的端口号，也就是说，任意两个进程的端口号不能相同，一旦相同，端口号冲突，
 *    那么这两个进程只能运行一个，不能同时运行。
 *    ③并不是每个进程都有端口号，只有需要进行网络通信的进程才有端口号。
 *
 *    端口号的取值范围是整数 0~65535。
 *     端口分类：①公认端口：0~1023。被预先定义的服务通信占用
 *    （如：HTTP占用端口80，FTP（远程传输协议）占用端口21，Telnet（远程登录协议）占用端口23）
 *                ②注册端口：1024~49151。分配给用户进程或应用程序。
 *    （如：Tomcat占用端口8080，MySQL占用端口3306，Oracle占用端口1521等）。
 *                ③ 动态/私有端口：49152~65535。
 *
 * 7. 端口号与IP地址的组合得出一个网络套接字：Socket
 *    也就是说，根据一个Socket能确定某台计算机的某个进程，网络通信实质上就是多台计算机的进程之间的
 *    通信，所以网络通信也称为Socket通信。
 *
 *     Socket允许程序把网络连接当成一个流，数据在两个Socket间通过IO传输。
 *     一般主动发起通信的应用程序属客户端，等待通信请求的为服务端。
 *
 * 8. 网络协议：见InetAddressTest2.java
 *
 * @author shkstart
 * @create 2019 下午 2:30
 */
public class InetAddressTest {

    public static void main(String[] args) {

        /*
        InetAddress类构造器是私有的，创建InetAddress对象有两个方法：
        1. getByName(String host) ：根据IP或域名创建InetAddress对象
        2. getLocalHost()：创建本机的InetAddress对象

        InetAddress对象的三个常用方法：
        ①public String getHostAddress()：返回 IP 地址字符串（即IP地址）。
        ②public String getHostName()：获取此 IP 地址的主机名（即域名）。
        ③public boolean isReachable(int timeout)：测试是否可以达到该地址。
                    （参数timeout的单位是毫秒，即在timeout毫秒时间内，
                      当前计算机能否连通到该InetAddress对象的IP的计算机）
         */
        try {
            InetAddress inet1 = InetAddress.getByName("192.168.10.14");

            System.out.println(inet1);// 输出:  /192.168.10.14

            InetAddress inet2 = InetAddress.getByName("www.atguigu.com");
            System.out.println(inet2);//输出： www.atguigu.com/219.238.20.86

            InetAddress inet3 = InetAddress.getByName("127.0.0.1");
            System.out.println(inet3);//   /127.0.0.1

            //获取本地ip
            InetAddress inet4 = InetAddress.getLocalHost();
            System.out.println(inet4);//输出:   WIN-8U0R1C9H3S4/192.168.1.105

            //getHostName()
            System.out.println(inet2.getHostName());//   www.atguigu.com
            //getHostAddress()
            System.out.println(inet2.getHostAddress());//  219.238.20.86

            System.out.println(inet2.isReachable(2000));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
