package per.wsk.java.study02;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author shkstart
 * @create 2019 上午 10:40
 *
 *
 * 字符流
 *
 * 字符的输入流和输出流
 *
 */
public class FileReaderWriterTest {

    public static void main(String[] args) {
        //因为现在是在main方法中，所以这里的相对路径是相较于当前工程的
        File file = new File("hello.txt");
        System.out.println(file.getAbsolutePath());

        File file1 = new File("08_25_IO\\hello.txt");
        System.out.println(file1.getAbsolutePath());
    }

    /*
    将hello.txt文件内容读入程序中，并输出到控制台

    说明点：
    1. read()的理解：返回读入的一个字符。如果达到文件末尾，再读就返回-1
    2. 异常的处理：为了保证流资源一定可以执行关闭操作。需要使用try-catch-finally处理
    3. 读入的文件一定要存在，否则就会报FileNotFoundException。

     */
    @Test
    public void testFileReader(){
        FileReader fr = null;
        try {
            //1.实例化File类的对象，指明要操作的文件
            File file = new File("hello.txt");
            //2.提供具体的流
            fr = new FileReader(file);

            //3.数据的读入
            //read():返回读入的一个字符。如果达到文件末尾，返回-1
            //方式一：
//        int data = fr.read();
//        while(data != -1){
//            System.out.print((char)data);
//            data = fr.read();
//        }

            //方式二：语法上针对于方式一的修改
            int data;
            //把reader.read()赋值给data，再判断data的值是否是-1
            while((data = fr.read()) != -1){
                System.out.print((char)data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.流的关闭操作
//            try {
//                if(fr != null)
//                    fr.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            //或
            if(fr != null){
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            /*
            垃圾回收机制只回收JVM堆内存里的对象空间。
            对其他物理连接，比如数据库连接、输入流输出流、Socket连接无能为力。
            所以对于这些连接需要手动关闭。
             */
        }
    }

    //对read()操作升级：使用read的重载方法
    //上面的方法一次只读两个字节。读的次数太多。效率慢。
    @Test
    public void testFileReader1()  {
        FileReader fr = null;
        try {
            //1.File类的实例化
            File file = new File("hello.txt");

            //2.FileReader流的实例化
            fr = new FileReader(file);

            //3.读入的操作
            //read(char[] cbuf):返回每次读入cbuf数组中的字符的个数。每次最多读入的个数是cbuf这个数组的长度，
            // 如果一个都没有读入，返回-1
            char[] cbuf = new char[5];
            int len;
            StringBuilder sb = new StringBuilder();
            //先把fr.read(cbuf)的返回值赋值给len，再判断len是否是-1，如果是-1说明文件读取完毕。
            while((len = fr.read(cbuf)) != -1){
                //方式一：错误的原因需要重点理解
                //错误的写法
//                for(int i = 0;i < cbuf.length;i++){
//                    System.out.print(cbuf[i]);
//                }
                //正确的写法
//                for(int i = 0;i < len;i++){
//                    System.out.print(cbuf[i]);
//                }
                //方式二：错误的原因需要重点理解
                //错误的写法,对应着方式一的错误的写法
//                String str = new String(cbuf);
//                System.out.print(str);
                //正确的写法   这个是String的一个构造器，下面的这行代码表示从cbuf这个char类型的数组的
                // 下标是0的字符一直向后数len个字符，这len个字符组合成str这个字符串。
                String str = new String(cbuf,0,len);
                System.out.print(str);

                //下面这个方法表示从cbuf这个char类型的数组的下标是0的元素开始向后数len个元素，
                //这些元素拼接到sb这个对象中。
                sb.append(cbuf,0,len);
            }
            System.out.println("\n"+sb);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fr != null){
                //4.资源的关闭
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }


    /*
    从内存中写出数据到硬盘的文件里。

    说明：
    1. 输出操作，输出对应的File可以不存在的。并不会报异常
        但如果File所在的文件夹就是不存在的文件夹，此时会报异常。
    2.
         File对应的硬盘中的文件如果不存在，在输出的过程中，会自动创建此文件。
          但如果File所在的文件夹就是不存在的文件夹，此时不会自动创建此该文件所在的文件夹。

         File对应的硬盘中的文件如果存在：
                如果流使用的构造器是：FileWriter(file,false) / FileWriter(file):对原有文件内容的覆盖
                如果流使用的构造器是：FileWriter(file,true):不会对原有文件覆盖，而是在原有文件基础上追加内容

     */
    @Test
    public void testFileWriter() {
        FileWriter fw = null;
        try {
            //1.提供File类的对象，指明写出到的文件
            File file = new File("hello1.txt");

            //2.提供FileWriter的对象，用于数据的写出
            fw = new FileWriter(file,false);

            //3.写出的操作
            fw.write("I have a dream!\n");
            fw.write("you need to have a dream!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.流资源的关闭
            if(fw != null){
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
    将一个文件内容写入到另一个文件中(文件的复制)
     */
    @Test
    public void testFileReaderFileWriter() {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            //1.创建File类的对象，指明读入和写出的文件
            File srcFile = new File("hello.txt");
            File destFile = new File("hello2.txt");

            //不能使用字符流来处理图片等字节数据
//            File srcFile = new File("爱情与友情.jpg");
//            File destFile = new File("爱情与友情1.jpg");


            //2.创建输入流和输出流的对象
            fr = new FileReader(srcFile);
            fw = new FileWriter(destFile);


            //3.数据的读入和写出操作
            char[] cbuf = new char[5];
            int len;//记录每次读入到cbuf数组中的字符的个数
            while((len = fr.read(cbuf)) != -1){
                //从cbuf数组的下标是0的元素开始，往后数len个字符，每次写出len个字符
                fw.write(cbuf,0,len);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.关闭流资源
            //方式一：
//            try {
//                if(fw != null)
//                    fw.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }finally{
//                try {
//                    if(fr != null)
//                        fr.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
            //方式二：
            try {
                if(fw != null)
                    fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if(fr != null)
                    fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //下面这种写法是不合适的，因为如果fw在关闭时出现异常，fr就没有关闭。
            /*try {
                if(fw != null)
                    fw.close();
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }

    }

}
