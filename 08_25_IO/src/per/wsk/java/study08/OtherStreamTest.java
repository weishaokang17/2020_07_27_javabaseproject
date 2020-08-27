package per.wsk.java.study08;

import org.junit.Test;

import java.io.*;

/**
 * 其他流的使用
 * 1.标准的输入、输出流
 * 2.打印流
 * 3.数据流
 *
 * @author shkstart
 * @create 2019 下午 6:11
 */
public class OtherStreamTest {

    /*
    System类底层是私有的构造方法。

    1.标准的输入、输出流
    1.1
    in是System类中的一个static final修饰的 InputStream(字节输入流基类)类型的常量。
    out是System类中的一个static final修饰的 PrintStream(打印流，打印量属于字节输出流
    ，也属于处理流)类型的常量。

    System.in:标准的输入流，默认从键盘输入
    System.out:标准的输出流，默认从控制台输出
    1.2
    System类的setIn(InputStream is) / setOut(PrintStream ps)方式重新指定in和out的值。
    System类中默认in的值是键盘，默认out的值是显示器。

    因为in和out在System中是static final修饰的，应该不能改变值才对，上面提到可以用
    setIn方法和setOut方法修改in和out的值，这个原因应该和底层c和c++有关，这个暂时不追究原因。

    1.3练习：
    从键盘输入字符串，要求将读取到的整行字符串转成大写输出。然后继续进行输入操作，
    直至当输入“e”或者“exit”时，退出程序。

    方法一：使用Scanner实现，调用next()返回一个字符串
    方法二：使用System.in实现。System.in  --->  转换流 ---> BufferedReader的readLine()

     */

    //IDEA中不支持junit从键盘输入
    public static void main(String[] args) {
        //缓冲流
        BufferedReader br = null;
        try {
            //先创建转换流，把字节输入流转为字符输入流
            InputStreamReader isr = new InputStreamReader(System.in);
            //造一个缓冲输入流对象，因为缓冲输入流的构造方法有带一个 字符输入流 参数的构造器。
            br = new BufferedReader(isr);

            while (true) {
                System.out.println("请输入字符串：");
                String data = br.readLine();
                if ("e".equalsIgnoreCase(data) || "exit".equalsIgnoreCase(data)) {
                    System.out.println("程序结束");
                    break;
                }

                String upperCase = data.toUpperCase();
                System.out.println(upperCase);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    /*
    2. 打印流：PrintStream (字节打印流)和PrintWriter（字符打印流）
               打印量也是一种处理流，打印流都是输出流。
         System类中的out这个变量的类型就是PrintStream，是static final修饰的。
         默认System类中的out这个变量默认值是显示器

    2.1 打印流PrintStream和PrintWriter中都提供了一系列重载的print() 和 println()
    2.2 练习：
     */
    @Test
    public void test2() {
        PrintStream ps = null;
        try {
            //先创建字节输出流
            FileOutputStream fos = new FileOutputStream(new File("text.txt"));
            // 创建打印输出流,设置为自动刷新模式(写入换行符或字节 '\n' 时都会刷新输出缓冲区)
            ps = new PrintStream(fos, true);
            if (ps != null) {// 把标准输出流(控制台输出)改成文件，
                // （即System的out属性值变成了ps这个打印流，不再是显示器）
                System.setOut(ps);
            }


            for (int i = 0; i <= 255; i++) { // 输出ASCII字符
                System.out.print((char) i);
                if (i % 50 == 0) { // 每50个数据一行
                    System.out.println(); // 换行
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }

    }

    /*
    3. 数据流
    3.1 DataInputStream（字节流、输入流、数据流（数据量也是处理流的一种））
        和
        DataOutputStream （字节流、输出流、数据流（数据量也是处理流的一种））

    3.2 作用：用于读取或写出基本数据类型的变量或字符串

    练习：将内存中的字符串、基本数据类型的变量写出到文件中。

    注意：处理异常的话，仍然应该使用try-catch-finally.
     */
    @Test
    public void test3() throws IOException {
        //1.先创建字节输出流，再把字节输出流当作参数，创建数据输出流。
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("data.txt"));
        //2.
        dos.writeUTF("刘建辰");
        dos.flush();//刷新操作，将内存中的数据写入文件
        dos.writeInt(23);
        dos.flush();
        dos.writeBoolean(true);
        dos.flush();
        //3.
        dos.close();


    }
    /*
    将文件中存储的基本数据类型变量和字符串读取到内存中，保存在变量中。

    注意点：读取不同类型的数据的顺序要与当初写入文件时，保存的数据的顺序一致！

     */
    @Test
    public void test4() throws IOException {
        //1.先创建字节输入流，再把字节输入流当成参数，创建数据输入流。
        DataInputStream dis = new DataInputStream(new FileInputStream("data.txt"));
        //2.注意：数据输入流只能按照文本中内容的顺序读取，例如如果先读取int或boolean会报错。
        String name = dis.readUTF();
        int age = dis.readInt();
        boolean isMale = dis.readBoolean();

        System.out.println("name = " + name);
        System.out.println("age = " + age);
        System.out.println("isMale = " + isMale);

        //3.
        dis.close();

    }

}
