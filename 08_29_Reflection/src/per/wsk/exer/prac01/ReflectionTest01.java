package per.wsk.exer.prac01;

import org.junit.Test;
import per.wsk.java.study01.Person;
import per.wsk.java.study01.ReflectionTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author weishaokang
 * @date 2020-08-29 23:55
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class ReflectionTest01 {

    @Test
    public void test01() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = Student.class;

        Constructor student = clazz.getConstructor(String.class,int.class);
        Student xiaoMing = (Student)student.newInstance("小明", 14);
        System.out.println(xiaoMing);

        Constructor constructor = clazz.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);
        Student xiaogang = (Student)constructor.newInstance("小刚");

        System.out.println(xiaogang);

        Constructor constructor1 = clazz.getConstructor();
        Student o = (Student)constructor1.newInstance();
        System.out.println(o);

    }

    @Test
    public void test02() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        //根据反射创建Student对象
        Class<Student> studentClass = Student.class;
        Constructor<Student> declaredConstructor = studentClass.getDeclaredConstructor(String.class);
        declaredConstructor.setAccessible(true);
        Student student = declaredConstructor.newInstance("卢俊义");

        //根据反射调用公共的没有参数的方法
        Method showMethod = studentClass.getDeclaredMethod("show");
        showMethod.invoke(student);

        //根据反射调用公共的带参数的方法
        Method setNameMethod = studentClass.getDeclaredMethod("setName", String.class);
        setNameMethod.invoke(student,"武松");

        System.out.println(student);

        //根据反射调用私有的带参数的方法
        Method showNationMethod = studentClass.getDeclaredMethod("showNation", String.class);
        showNationMethod.setAccessible(true);
        showNationMethod.invoke(student,"中国");

        //调用公共的属性
        Field age = studentClass.getDeclaredField("age");
        age.set(student,17);

        System.out.println(student);

        //调用私有的属性
        Field name = studentClass.getDeclaredField("name");
        name.setAccessible(true);
        name.set(student,"鲁智深");

        System.out.println(student);

        //凡是通过反射调用私有的结构，无论是调用私有的 构造器、方法、属性，
        //都要加上 setAccessible(true)

    }



    @Test
    public void test03() throws ClassNotFoundException {
        Class<Student> clazz01 = Student.class;

        Student student = new Student();
        Class<? extends Student> clazz02 = student.getClass();

//        Class clazz03 = Class.forName("Student");
        Class clazz03 = Class.forName("per.wsk.exer.prac01.Student");
        //上面的两行代码，第一行报错，第二行正确，即需要些类的全路径。

        Class clazz04 = Class.forName("java.lang.String");
        //clazz04是 String这个类

        System.out.println(clazz01 == clazz02);
        System.out.println(clazz02 == clazz03);

        ClassLoader classLoader = ReflectionTest01.class.getClassLoader();
        Class clazz05 = classLoader.loadClass("per.wsk.exer.prac01.Student");

        System.out.println(clazz05 == clazz03);
    }


}
