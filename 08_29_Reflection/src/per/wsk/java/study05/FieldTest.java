package per.wsk.java.study05;

import org.junit.Test;
import per.wsk.java.study04.Person;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 获取当前运行时类的属性结构
 *
 * @author shkstart
 * @create 2019 下午 3:23
 */
public class FieldTest {


    /*
    获取类中的属性（包含静态的）
     */
    @Test
    public void test1(){
        Class clazz = Person.class;

        //获取属性结构
        //getFields():获取当前运行时类及其父类中声明为public访问权限的属性，父类中的
        //static修饰的属性也可以获取到。
        Field[] fields = clazz.getFields();
        for(Field f : fields){
            System.out.println(f);
            //public static java.lang.String per.wsk.java.study04.Person.country
            //public int per.wsk.java.study04.Person.id
            //public double per.wsk.java.study04.Creature.weight
            //public static java.lang.String per.wsk.java.study04.Creature.lifeTime
        }
        /*
        下面这段代码是在out目录下面找到的上面针对数组进行增强for循环的反编译代码。
        可以看到，Collection集合增强for循环的底层原理是迭代器，但数组的增强for循环的底层
        是普通的for循环。

        Field[] declaredFields = fields;
        int var4 = fields.length;

        int var5;
        for(var5 = 0; var5 < var4; ++var5) {
            Field f = declaredFields[var5];
            System.out.println(f);
        }
         */
        System.out.println();


        //getDeclaredFields():获取当前运行时类中声明的所有属性（不光是修饰符是public的）。
        // （不包含父类中声明的属性）
        Field[] declaredFields = clazz.getDeclaredFields();
        for(Field f : declaredFields){
            System.out.println(f);
            //public static java.lang.String per.wsk.java.study04.Person.country
            //private java.lang.String per.wsk.java.study04.Person.name
            //int per.wsk.java.study04.Person.age
            //public int per.wsk.java.study04.Person.id
        }

    }



    //上面test1()方法是获取对象中的属性，事实上clazz.getDeclaredFields()不仅可以获取到
    //当前类中的所有属性，还可以获取到属性的权限修饰符和数据类型。
    // 权限修饰符  数据类型 变量名
    @Test
    public void test2(){
        Class clazz = Person.class;
        Field[] declaredFields = clazz.getDeclaredFields();
        for(Field f : declaredFields){
            //1.权限修饰符
            int modifier = f.getModifiers();
            //权限修饰符，0表示缺省  1表示public 2表示private 3表示protected,
            //详见Modifier这个类
            System.out.print(Modifier.toString(modifier) + "\t");

            //2.数据类型
            Class type = f.getType();
            System.out.print(type.getName() + "\t");

            //3.变量名
            String fName = f.getName();
            System.out.print(fName);

            System.out.println();
        }

        /*
        上面的输出结果：

        public static	java.lang.String	country
        private	        java.lang.String	name
	                                int	    age
        public	                    int	    id
         */

    }


}
