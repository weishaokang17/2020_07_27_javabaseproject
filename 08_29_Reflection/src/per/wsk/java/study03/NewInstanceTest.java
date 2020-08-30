package per.wsk.java.study03;

import org.junit.Test;
import per.wsk.java.study01.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

/**
 * 通过反射创建对应的运行时类的对象
 *
 * @author shkstart
 * @create 2019 下午 2:32
 */
public class NewInstanceTest {


    @Test
    public void test1() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Class<Person> clazz = Person.class;
        /*
        newInstance():调用此方法，创建对应的运行时类的对象。
        内部调用了运行时类的空参的构造器。

        要想此方法正常的创建运行时类的对象，要求：
        1.运行时类必须提供空参的构造器
        2.空参的构造器的访问权限得够。通常，设置为public。

        在javabean中要求提供一个public的空参构造器。原因：
        1.便于通过反射，创建运行时类的对象
        2.便于子类继承此运行时类时，默认调用super()时，保证父类有此构造器

         */
        //通过反射创建Person对象的方式一
        Person obj = clazz.newInstance();
        System.out.println(obj);

        //通过反射创建Person对象的方式二
        Constructor<Person> constructor = clazz.getConstructor();
        constructor.setAccessible(true);
        Person person = constructor.newInstance();
        System.out.println(person);

        //通过反射创建Person对象的方式三
        Constructor<Person> declaredConstructor = clazz.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        Person person1 = declaredConstructor.newInstance();
        System.out.println(person1);

        /*
        上面是通过反射创建Person对象的三种方式，方式一只能支持无参构造器，
        方式二和方式三既可以支持无参构造器，也可以支持带参构造器。
        方式一和方式二在代码写的地方必须有权限调用到要调用的构造器，即访问权限要足够。
        方式三没必要考虑权限的问题，即使构造器是private修饰的仍然可以访问到。
         */
    }



    //体会反射的动态性
    @Test
    public void test2(){
        for(int i = 0;i < 100;i++){
            int num = new Random().nextInt(3);//0,1,2
            String classPath = "";
            switch(num){
                case 0:
                    classPath = "java.util.Date";
                    break;
                case 1:
                    classPath = "java.lang.Object";
                    break;
                case 2:
                    classPath = "per.wsk.java.study01.Person";
                    break;
            }
            try {
                Object obj = getInstance(classPath);
                System.out.println(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /*
    创建一个指定类的对象。
    classPath:指定类的全类名
     */
    public Object getInstance(String classPath) throws Exception {
       Class clazz =  Class.forName(classPath);
       return clazz.newInstance();
    }

}
