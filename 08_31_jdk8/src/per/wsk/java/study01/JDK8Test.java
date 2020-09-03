package per.wsk.java.study01;

/**
 * @Author weishaokang
 * @date 2020-09-01 16:13
 * @project 2020_07_27_javabaseproject
 * @description:
 *
 * java8中的新特性：
 *
 * 1.接口中可以定义默认方法和静态方法
 * 2.新增了很多日期类、时间类
 * 3.集合框架有一些更改，例如 HashMap的底层结构，ArrayList的扩容方式
 *      ArrayList调用add方法时才开辟数组（类似于懒汉式）
 * 4. 增加了一种元注解，之前是4种元注解，现在是5种，增加的元注解是可重复注解
 * 5. jvm常量区之前在堆区，jdk8中挪到了方法区。
 * 6. lambda表达式和函数式接口
 * 7. 方法引用和构造器引用（lambda表达式的更简便的写法，符号是::）
 * 8. stream流
 *    Collection接口中有默认方法stream()。
 *    中间操作每一步都产生一个新的流。
 *   Stream对象以双向链表的形式组织在一起，构成整个流水线，
 *   由于每个Stage都记录了前一个Stage和本次的操作以及回调函数，
 *   依靠这种结构就能建立起对数据源的所有操作。
 *
 *   stream 和 iterator 迭代的效率比较：
 * - ①传统 iterator (for-loop) 比 stream(JDK8) 迭代性能要高，
 *      尤其在小数据量的情况下；
 * - ②在多核情景下，对于大数据量的处理，并行流可以有比iterator 更高的迭代处理效率；
 *
 * 9. Optional类，类中有一个属性value，value的类型是泛型，可以指定。
 *    Optional类相当于把保存的数据保存到Optional对象的value属性中，相当于
 *    把保存的数据在外面包装了一层，目的是避免空指针异常。
 */
public class JDK8Test {
}
