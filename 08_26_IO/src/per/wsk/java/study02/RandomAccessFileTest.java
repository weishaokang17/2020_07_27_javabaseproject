package per.wsk.java.study02;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * RandomAccessFile的使用
 *
 * RandomAccessFile并不继承IO流体系图的四个基类，它是继承的Object，所以它
 * 不属于IO流体系下的。所以，它不能说是属于字节流还是字符流、节点流或处理流、输入流或输出流。
 *
 *
 * 1.RandomAccessFile直接继承于java.lang.Object类，实现了DataInput和DataOutput接口
 * 2.RandomAccessFile既可以作为一个输入流，又可以作为一个输出流
 *
 * 3.如果RandomAccessFile作为输出流时，写出到的文件如果不存在，则在执行过程中自动创建。
 *   如果写出到的文件存在，则会对原有文件内容进行覆盖。（默认情况下，从头覆盖）
 *
 * 4. 可以通过相关的操作，实现RandomAccessFile“插入”数据的效果
 *
 *
 *
 * RandomAccessFile 类支持 “随机访问” 的方式，程序可以直接跳到文件的任意
 * 地方来读、写文件
 * 支持只访问文件的部分内容
 * 可以向已存在的文件后追加内容
 * RandomAccessFile 对象包含一个记录指针，用以标示当前读写处的位置。
 * RandomAccessFile 类对象可以自由移动记录指针：
 * long getFilePointer()：获取文件记录指针的当前位置
 * void seek(long pos)：将文件记录指针定位到 pos 位置
 * 构造器
 * public RandomAccessFile(File file, String mode)
 * public RandomAccessFile(String name, String mode) 创建 RandomAccessFile 类实例需要指定一个 mode 参数，该参数指
 * 定 RandomAccessFile 的访问模式：
 * r: 以只读方式打开
 * rw：打开以便读取和写入
 * rwd:打开以便读取和写入；同步文件内容的更新
 * rws:打开以便读取和写入；同步文件内容和元数据的更新
 *
 *
 *
 * 我们可以用RandomAccessFile这个类，来实现一个多线程断点下载的功能，
 * 用过下载工具的朋友们都知道，下载前都会建立两个临时文件，一个是与
 * 被下载文件大小相同的空文件，另一个是记录文件指针的位置文件，每次
 * 暂停的时候，都会保存上一次的指针，然后断点下载的时候，会继续从上
 * 一次的地方下载，从而实现断点下载或上传的功能，有兴趣的朋友们可以
 * 自己实现下。
 *
 * @author shkstart
 * @create 2019 上午 11:18
 */
public class RandomAccessFileTest {

    @Test
    public void test1() {

        RandomAccessFile raf1 = null;
        RandomAccessFile raf2 = null;
        try {
            //1.ref1只有读的功能， ref2既有读、又有写的功能。
            raf1 = new RandomAccessFile(new File("爱情与友情.jpg"),"r");
            raf2 = new RandomAccessFile(new File("爱情与友情1.jpg"),"rw");
            //2.
            byte[] buffer = new byte[1024];
            int len;
            while((len = raf1.read(buffer)) != -1){
                raf2.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //3.
            if(raf1 != null){
                try {
                    raf1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(raf2 != null){
                try {
                    raf2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test2() throws IOException {

        RandomAccessFile raf1 = new RandomAccessFile("hello.txt","rw");

        raf1.seek(3);//将指针调到角标为3的位置
        raf1.write("xyz".getBytes());//把原来文件的4、5、6三个位置替换成了x，y,z
        /*
        默认是从第一个位置开始替换。
        如果要在一个文件后面追加，那么就需要加获取这个文件的长度，
         然后用seek()方法让指针指到位置的最后的位置+1，然后开始写入。
         */

        raf1.close();

    }


    /*
    使用RandomAccessFile实现数据的插入效果
     */
    @Test
    public void test3() throws IOException {

        RandomAccessFile raf1 = new RandomAccessFile("hello.txt","rw");

        raf1.seek(3);//将指针调到角标为3的位置
        //保存指针3后面的所有数据到StringBuilder中

        //先指定builder的长度是源文件的内容的长度，因为length()方法返回的是long类型。
        StringBuilder builder = new StringBuilder((int) new File("hello.txt").length());
        byte[] buffer = new byte[20];
        int len;
        //把raf1从第4个位置开始读，一直读到最后，把所有读到的放到builder这个对象中。
        while((len = raf1.read(buffer)) != -1){
            builder.append(new String(buffer,0,len)) ;
        }
        //调回指针，写入“xyz”
        raf1.seek(3);
        raf1.write("xyz".getBytes());

        //将StringBuilder中的数据写入到文件中
        raf1.write(builder.toString().getBytes());

        raf1.close();

        //思考：将StringBuilder替换为ByteArrayOutputStream
    }

    //和上面test3()方法作用一样，但代码比test3()方法简单。
    @Test
    public void test04() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("test.txt", "rw");
        raf.seek(5);
        //先读出来
        String temp = raf.readLine();
        raf.seek(5);
        raf.write("xyz".getBytes());
        raf.write(temp.getBytes());
        raf.close();
    }
}
