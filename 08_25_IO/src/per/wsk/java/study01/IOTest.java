package per.wsk.java.study01;

/**
 * @Author weishaokang
 * @date 2020-08-25 15:51
 * @project 2020_07_27_javabaseproject
 * @description:
 *
 *  关于IO流的最基本的一些介绍，先看一些印象笔记中的记录。
 *
 * 一、Java中流的体系非常复杂，Java的IO流共涉及40多个类，不过基础的流是下面四个。
 *  (抽象基类)      字节流         字符流
 *   输入流         InputStream     Reader
 *   输出流         OutputStream    Writer
 *
 * 二、IO流体系简介
 *    详见与该文件同一个包下的 IO流体系图 这张图片。
 *
 * 三、
 *   抽象基类         节点流（或文件流）                               缓冲流（处理流的一种）
 *  InputStream     FileInputStream   (read(byte[] buffer))        BufferedInputStream (read(byte[] buffer))
 *  OutputStream    FileOutputStream  (write(byte[] buffer,0,len)  BufferedOutputStream (write(byte[] buffer,0,len) / flush()
 *  Reader          FileReader (read(char[] cbuf))                 BufferedReader (read(char[] cbuf) / readLine())
 *  Writer          FileWriter (write(char[] cbuf,0,len)           BufferedWriter (write(char[] cbuf,0,len) / flush()
 *
 *
 *
 */
public class IOTest {


}
