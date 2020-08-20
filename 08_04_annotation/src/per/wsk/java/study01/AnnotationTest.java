package per.wsk.java.study01;



import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Date;

/**
 * 注解的使用
 *
 * 一. 理解Annotation:
 * ① jdk 5.0 新增的功能
 *
 * ② Annotation 其实就是代码里的特殊标记, 这些标记可以在编译, 类加载, 运行时被读取,
 * 并执行相应的处理。通过使用 Annotation,
 * 程序员可以在不改变原有逻辑的情况下, 在源文件中嵌入一些补充信息。
 *
 * ③在JavaSE中，注解的使用目的比较简单，例如标记过时的功能，忽略警告等。在JavaEE/Android
 * 中注解占据了更重要的角色，例如用来配置应用程序的任何切面，代替JavaEE旧版中所遗留的繁冗
 * 代码和XML配置等。
 *
 * ④未来的开发模式都是基于注解的，JPA是基于注解的，Spring2.5以
 * 上都是基于注解的，Hibernate3.x 以后也是基于注解的，现在的
 * Struts2有一部分也是基于注解的了，注解是一种趋势，一定程度上
 * 可以说：框架 = 注解 + 反射 + 设计模式。
 *
 * 二. Annocation的使用示例
 * 使用 Annotation 时要在其前面增加 @ 符号, 并把该 Annotation 当成
 * 一个修饰符使用。用于修饰它支持的程序元素
 *
 * 示例一：生成文档相关的注解
 *      @author 标明开发该类模块的作者，多个作者之间使用,分割
 *      @version 标明该类模块的版本
 *      // @see 参考转向，也就是相关主题
 *      @since 从哪个版本开始增加的
 *      // @param 对方法中某参数的说明，如果没有参数就不能写
 *      @return 对方法返回值的说明，如果方法的返回值类型是void就不能写
 *      // @exception 对方法可能抛出的异常进行说明 ，如果方法没有用throws显式抛出的异常就不能写
 *
 *      其中
        @param @return 和 @exception 这三个标记都是只用于方法的。
        @param的格式要求：@param 形参名 形参类型 形参说明
        @return 的格式要求：@return 返回值类型 返回值说明
        @exception的格式要求：@exception 异常类型 异常说明
        @param和@exception可以并列多个

 * 示例二：在编译时进行格式检查(JDK内置的三个基本注解)
     @Override: 限定重写父类方法, 该注解只能用于方法
     @Deprecated: 可以用于类、方法、变量上，
     用于表示所修饰的元素(类, 方法等)已过时。通常是因为所修饰的结构危险或存在更好的选择
     @SuppressWarnings: 可以用于类、方法、变量上。作用是抑制编译器警告

  * 示例三：跟踪代码依赖性，实现替代配置文件功能
  *     ①Servlet3.0提供了注解(annotation),使得不再需要在web.xml文件中进行Servlet的部署
        ②spring框架中关于“事务”的管理

  * 三. 如何自定义注解：参照@SuppressWarnings定义
      * ① 注解声明为：@interface
      * ② 自定义注解自动继承了java.lang.annotation.Annotation接口
      * ③ 注解的属性也叫做成员变量。注解只有成员变量，没有方法。
      * 注解的成员变量前面没有任何修饰符。
      * 注解的成员变量在注解的定义中以“无形参的方法”形式来声明，
      * 其方法名定义了该成员变量的名字，其返回值定义了该成员变量的类型。
      * 如：int id();
      *     String msg();
      * 表示该注解有两个成员变量，int类型的id和String类型的msg。
      *
      * ④ 如果只有一个成员变量，建议这个成员变量使用参数名为value
      * ⑤ 可以在定义 Annotation 的成员变量时为其指定初始值, 指定成员变量的初始
      * 值可使用 default 关键字
      * ⑥ 如果定义的注解含有成员变量，那么使用时必须指定成员变量的值，除非它有默认
      * 值。格式是“参数名 = 参数值”，如果只有一个参数成员，且名称为value，
      * 可以省略“value=”
      * ⑦ 如果自定义注解没有成员，表明该注解只起到一个标识作用。
      * 没有成员变量的 Annotation通常称为标记; 包含成员变量的 Annotation 通常称为元数
      * 据Annotation

     如果注解有成员变量，在使用注解时，需要指明成员的值。
     自定义注解必须配上注解的信息处理流程(使用反射)才有意义。
     自定义注解通过都会指明两个元注解：Retention、Target

     四. jdk 提供的4种元注解
       元注解：对现有的注解进行解释说明的注解
     ①Retention：指定所修饰的 Annotation 的生命周期：SOURCE\CLASS（默认行为）\RUNTIME
            只有声明为RUNTIME生命周期的注解，才能通过反射获取。
     Annotation 的生命周期有以下三种：
     1.RetentionPolicy.SOURCE:在源文件中有效（即源文件保留），编译器直接丢弃这种策略的
     注释
     2.RetentionPolicy.CLASS:在class文件中有效（即class保留） ， 当运行 Java 程序时, JVM
     不会保留注解。 这是默认值
     3.RetentionPolicy.RUNTIME:在运行时有效（即运行时保留），当运行 Java 程序时, JVM 会
     保留注解。程序可以通过反射获取该注解。

     ②Target:用于修饰 Annotation 定义, 用于指定被修饰的 Annotation 能用于
     修饰哪些程序元素。 @Target 也包含一个名为 value 的成员变量，类型是ElementType类型的数组。
     共有以下几种取值：
     ElementType.ANNOTATION_TYPE 可以给一个注解进行注解
     ElementType.CONSTRUCTOR 可以给构造方法进行注解
     ElementType.FIELD 可以给属性进行注解
     ElementType.LOCAL_VARIABLE 可以给局部变量进行注解
     ElementType.METHOD 可以给方法进行注解
     ElementType.PACKAGE 可以给一个包进行注解
     ElementType.PARAMETER 可以给一个方法内的参数进行注解
     ElementType.TYPE 可以给一个类型进行注解，比如类、接口、枚举


     *******下面两个元注解出现的频率较低*******

     ③Documented:用于指定被该元 Annotation 修饰的 Annotation 类将被
     javadoc 工具提取成文档。默认情况下，javadoc是不包括注解的。

     注意：定义为Documented的注解必须设置Retention值为RUNTIME

     ④Inherited:被它修饰的 Annotation 将具有继承性，即父类A上面有这个注解，这个注解指明了Inherited，
     那么父类A的子类也有该注解。

     五.通过反射获取注解信息 ---到反射内容时系统讲解

     六. jdk 8 中注解的新特性：可重复注解、类型注解

     6.1 可重复注解：① 在MyAnnotation上声明@Repeatable，成员值为MyAnnotations.class
                    ② MyAnnotation的Target、Retention、Inherited三个元注解与MyAnnotations相同。

     6.2 类型注解：
     ElementType.TYPE_PARAMETER 表示该注解能写在类型变量的声明语句中（如：泛型声明）。
     ElementType.TYPE_USE 表示该注解能写在使用类型的任何语句中。

      *
 * @author shkstart
 * @create 2019 上午 11:37
 */

public class AnnotationTest {

    public static void main(String[] args) {
        Person p = new Student();
        p.walk();

        Date date = new Date(2020, 10, 11);
        System.out.println(date);

        @SuppressWarnings("unused")
        int num = 10;

//        System.out.println(num);

        //SuppressWarnings的源码点进去，里面有一个String类型的数组value,所以像下面这样
        // 注解后面可以填数组格式的很多字符串
        @SuppressWarnings({ "unused", "rawtypes" })
        ArrayList list = new ArrayList();
    }

    @Test
    public void testGetAnnotation(){
        Class clazz = Student.class;
        Annotation[] annotations = clazz.getAnnotations();
        for(int i = 0;i < annotations.length;i++){
            System.out.println(annotations[i]);
            //输出：只显示类的注解，类中方法的注解或变量的注解不会显示
            /*@per.wsk.java.study01.MyAnnotations(value={@per.wsk.java.study01.MyAnnotation(value="hi"),
                    @per.wsk.java.study01.MyAnnotation(value="abc")})*/
        }
    }
}


//jdk 8之前的写法：
//@MyAnnotations({@MyAnnotation(value="hi"),@MyAnnotation(value="hi")})
//jdk1.8之前，想要使用可重复注解，必须要按照上面这两行的格式。jdk1.8之后，可以写成下面的格式。jdk1.8新增了可重复
//注解的功能
@MyAnnotation(value="hi")
@MyAnnotation(value="abc")
class Person{
    private String name;
    private int age;

    public Person() {
    }
    @MyAnnotation
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    @MyAnnotation
    public void walk(){
        System.out.println("人走路");
    }
    public void eat(){
        System.out.println("人吃饭");
    }
}

interface Info{
    void show();
}

class Student extends Person implements Info {

    @Override
    public void walk() {
        System.out.println("学生走路");
    }


    public void show() {

    }
}
/*
jdk8.0 的target新增了 ElementType.TYPE_PARAMETER和 ElementType.TYPE_USE
如果MyAnnotation这个注解的target的value属性不包含ElementType.TYPE_PARAMETER，
下面类名后面的泛型前的注解会报错。
 */
class Generic<@MyAnnotation T>{

    //如果MyAnnotation这个注解的target的value属性不包含ElementType.TYPE_USE，
    //下面这几个地方用的MyAnnotation这个注解都会报错。
    public void show() throws @MyAnnotation RuntimeException{

        ArrayList<@MyAnnotation String> list = new ArrayList<>();

        int num = (@MyAnnotation int) 10L;
    }

}