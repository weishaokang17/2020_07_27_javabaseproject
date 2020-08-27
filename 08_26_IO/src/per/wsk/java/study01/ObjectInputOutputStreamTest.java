package per.wsk.java.study01;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 对象流的使用
 * 1.ObjectInputStream 和 ObjectOutputStream
 * 2.作用：用于存储和读取基本数据类型数据或对象的处理流。
 *      它的强大之处就是可以把Java中的对象写入到数据源中，也能把对象从数据源中还原回来。
 *
 * 3.要想一个java对象是可序列化的，需要满足相应的要求。见Person.java
 *
 * 4.序列化机制：
 * 序列化：对象序列化机制允许把内存中的Java对象转换成平台无关的二进制流，从而允许把这种
 * 二进制流持久地保存在磁盘上，或通过网络将这种二进制流传输到另一个网络节点。
 * 反序列化：当其它程序获取了这种二进制流，就可以恢复成原来的Java对象。

   5.
    序列化的好处在于可将任何实现了Serializable接口的对象转化为字节数据，
    使其在保存和传输时可被还原
    序列化是 RMI（Remote Method Invoke – 远程方法调用）过程的参数和返
    回值都必须实现的机制，而 RMI 是 JavaEE 的基础。因此序列化机制是
    JavaEE 平台的基础
    如果需要让某个对象支持序列化机制，则必须让对象所属的类及其属性是可
    序列化的，为了让某个类是可序列化的，该类必须实现Serializable和Externalizable
    两个接口之一，其中Externalizable接口继承了Serializable接口，所以可以认为：
    要想让某个类可以序列化，这个类必须实现Serializable接口。
    否则，会抛出NotSerializableException异常

    Serializable接口是标识接口，里面什么内容都没有
 *
 * @author shkstart
 * @create 2019 上午 10:27
 */
public class ObjectInputOutputStreamTest {


    /*
    ObjectInputStream: 对象输入流    属于输入流、字节流、处理流
    ObjectOutputStream：对象输出流   属于输出流、字节流、处理流
     */

    /*
    序列化过程：将内存中的java对象保存到磁盘中或通过网络传输出去
    使用ObjectOutputStream实现
     */
    @Test
    public void testObjectOutputStream(){
        ObjectOutputStream oos = null;

        try {
            //1.先创建一个节点流、也是输出的字节流，再把这个字节流当成属性创建对象输出流。
            oos = new ObjectOutputStream(new FileOutputStream("object.dat"));
            //2.
            oos.writeObject(new String("我爱北京天安门"));
//            oos.flush();//刷新操作，可以省略

            oos.writeObject(new Person("王铭",23));
//            oos.flush();

            oos.writeObject(new Person("张学良",23,1001,new Account(5000)));
//            oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(oos != null){
                //3.
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    /*
    反序列化：将磁盘文件中的对象还原为内存中的一个java对象
    使用ObjectInputStream来实现
     */
    @Test
    public void testObjectInputStream(){
        ObjectInputStream ois = null;
        try {
            //先创建一个节点流，也是字节输入流，再把它当成属性创建对象输入流。
            ois = new ObjectInputStream(new FileInputStream("object.dat"));

            //读取的时候（也就是反序列化的时候），读写顺序一定要和之前写出的顺序（也就是序列化
            // 顺序相同），否则会报类型转换异常
            Object obj = ois.readObject();
            String str = (String) obj;

            Person p = (Person) ois.readObject();
            Person p1 = (Person) ois.readObject();

            System.out.println(str);
            System.out.println(p);
            System.out.println(p1);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }



    }

}
