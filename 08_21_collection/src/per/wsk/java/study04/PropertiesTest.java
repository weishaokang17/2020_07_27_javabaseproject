package per.wsk.java.study04;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author shkstart
 * @create 2019 下午 4:07
 *
 *
 *
 * 1. Properties 类是 Hashtable 的子类，底层也是顺序表+链表+红黑树，
 *    该对象用于处理属性文件
 * 2. 由于属性文件里的 key、value 都是字符串类型，所以 Properties 里的 key
 *    和 value 都是字符串类型
 * 3. 存取数据时，一般使用setProperty(String key,String value)方法和
 *    getProperty(String key)方法。用put和get也能实现存取功能，setProperty和getProperty
 *    这两个方法是Properties独有的。
 *
 */
public class PropertiesTest {

    //Properties:常用来处理配置文件。key和value都是String类型
    public static void main(String[] args)  {
        FileInputStream fis = null;
        try {
            Properties pros = new Properties();
            //下面的路径一定要写对，否则会报找不到文件的异常
            fis = new FileInputStream("jdbc.properties");
            pros.load(fis);//加载流对应的文件

            Object name1 = pros.get("name");
            System.out.println("-----------------"+name1);

            String name = pros.getProperty("name");
            String password = pros.getProperty("password");
            String password1 = pros.getProperty("password1");

            System.out.println("name = " + name + ", password = " + password);
            //name = 魏少康celtics, password = abc123
            System.out.println(password1);
            //null      因为jdbc.properties中没有password1 这个key ，所以得到的password1是null
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }
}
