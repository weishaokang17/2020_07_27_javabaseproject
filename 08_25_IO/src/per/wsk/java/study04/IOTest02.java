package per.wsk.java.study04;

/**
 * @Author weishaokang
 * @date 2020-08-25 19:51
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class IOTest02 {
    /*
    一、InputStream 和 Reader 是所有输入流的基类。
    ①InputStream（典型实现：FileInputStream）
    int read()
    int read(byte[] b)
    int read(byte[] b, int off, int len)
    ②Reader（典型实现：FileReader）
    int read()
    int read(char [] c)
    int read(char [] c, int off, int len)
    ③程序中打开的文件 IO 资源不属于内存里的资源，垃圾回收机制无法回收该资
    源，所以应该显式关闭文件 IO 资源。
     */


    /*
    InputStream

    ① int read()
    从输入流中读取数据的下一个字节。返回 0 到 255 范围内的 int 字节值。如果因
    为已经到达流末尾而没有可用的字节，则返回值 -1。
    ② int read(byte[] b)
    从此输入流中将最多 b.length 个字节的数据读入一个 byte 数组中。如果因为已
    经到达流末尾而没有可用的字节，则返回值 -1。否则以整数形式返回实际读取
    的字节数。
    与调用下面第③个方法 int read(char[] cbuf,0,cbuf.length)效果是一样的。
    ③ int read(byte[] b, int off,int len)
    将字节读入数组的某一部分。存到数组cbuf中，从数组下标的off处开始存储，
    将输入流中最多 len 个数据字节读入 byte 数组。尝试读取 len 个字节，但读取
    的字节也可能小于该值。以整数形式返回实际读取的字节数。如果因为流位于
    文件末尾而没有可用的字节，则返回值 -1。
    ④ public void close() throws IOException
    关闭此输入流并释放与该流关联的所有系统资源。
     */


    /*
    Reader


    ① int read()
    读取单个字符。作为整数读取的字符，范围在 0 到 65535 之间 (0x00-0xffff)（2个
    字节的Unicode码），如果已到达流的末尾，则返回 -1
    ② int read(char[] cbuf)
    将字符读入数组。如果已到达流的末尾，则返回 -1。否则返回本次读取的字符数。
    与调用下面第③个方法 int read(char[] cbuf,0,cbuf.length)效果是一样的。
    ③ int read(char[] cbuf,int off,int len)
    将字符读入数组的某一部分。存到数组cbuf中，从数组下标的off处开始存储，最多读len个字
    符。如果已到达流的末尾，则返回 -1。否则返回本次读取的字符数。
    ④ public void close() throws IOException
    关闭此输入流并释放与该流关联的所有系统资源。
     */







    /*
    OutputStream & Writer
    ① OutputStream 和 Writer 也非常相似：
     void write(int b/int c);
     void write(byte[] b/char[] cbuf);
     void write(byte[] b/char[] buff, int off, int len);
     void flush();
     void close(); 需要先刷新，再关闭此流
    ② 因为字符流直接以字符作为操作单位，所以 Writer 可以用字符串来替换字符数组，
    即以 String 对象作为参数
     void write(String str);
     void write(String str, int off, int len);
    ③ FileOutputStream 从文件系统中的某个文件中获得输出字节。FileOutputStream
    用于写出非文本数据之类的原始字节流。要写出字符流，需要使用 FileWriter
     */




    /*
        OutputStream

    void write(int b)
    将指定的字节写入此输出流。write 的常规协定是：向输出流写入一个字节。要写
    入的字节是参数 b 的八个低位。b 的 24 个高位将被忽略。 即写入0~255范围的。
    void write(byte[] b)
    将 b.length 个字节从指定的 byte 数组写入此输出流。write(b) 的常规协定是：应该
    与调用 write(b, 0, b.length) 的效果完全相同。
    void write(byte[] b,int off,int len)
    将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此输出流。
    public void flush()throws IOException
    刷新此输出流并强制写出所有缓冲的输出字节，调用此方法指示应将这些字节立
    即写入它们预期的目标。
    public void close() throws IOException
    关闭此输出流并释放与该流关联的所有系统资源。

     */



    /*
    Writer

    ① void write(int c)
    写入单个字符。要写入的字符包含在给定整数值的 16 个低位中，16 高位被忽略。 即
    写入0 到 65535 之间的Unicode码。
    ② void write(char[] cbuf)
    写入字符数组。
    ③ void write(char[] cbuf,int off,int len)
    写入字符数组的某一部分。从off开始，写入len个字符
    ④ void write(String str)
    写入字符串。与②作用一样。
    ⑤ void write(String str,int off,int len)
    写入字符串的某一部分。 与③作用一样。
    ⑥ void flush()
    刷新该流的缓冲，则立即将它们写入预期目标。
    ⑦ public void close() throws IOException
    关闭此输出流并释放与该流关联的所有系统资源

     */

}
