package per.wsk.java.study01;


/**
 * @author shkstart
 * @create 2019 上午 11:59
 *
 *  jdk9新特性：
 *  ① jdk目录结构发生变化
 *  ②Java 的 REPL 工具： jShell 命令
 *  ③语法改进：接口的私有方法
 *  ④底层结构：String 存储结构变更（这个很重要）
 *  ⑤集合工厂方法：快速创建只读集合
 *
 *
 *  jdk10新特性：
 *  ①局部变量的类型推断 var 关键字
 *
 *  jdk11新特性：
 *  本地变量类型推断
 *  字符串加强
 *  集合加强
 *  Stream 加强
 *  Optional 加强
 *  InputStream 加强
 *  HTTP Client API
 *  化繁为简，一个命令编译运行源代码
 *
 */
public class ModuleTest {

    public static void main(String[] args) {
        Person p = new Person("Tom",12);
        System.out.println(p);
    }

}
