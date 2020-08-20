package per.wsk.java.study01;

/**
 * 一、满足枚举类的条件：
 *      当一个类满足以下条件时称为枚举类:
 *    ①这个类的构造器必须全是私有的
 *    ②因构造器是私有的，所以该类的对象必须在当前类中声明。 该类的对象必须是有限个，
 *    且这有限个对象都必须被public static final修饰。
 *
 *    同时满足上面两个条件，我们就说这个类是枚举类
 *
 *    一般情况下，枚举类中的属性我们都用private final修饰。
 *    首先，先解释为什么用private修饰，因为平时类的属性基本都是private修饰的。
 *    然后，因为上面枚举类的第②个条件说明枚举类的对象必须用
 *    final修饰，对象不可变，那么一般情况下，我们把对象的属性也都设置成不可变的了，
 *    故枚举类的属性一般也用final修饰。
 *
 *    当然，属性要用private final修饰这个并不是枚举类所必需的条件，即一个枚举类
 *    如果里面有些属性不用private final修饰也不报错。只是一般情况下，我们都把枚举类
 *    的属性设置成private final修饰而已。
 *
 *    需要注意：一旦属性设置成final修饰，那么由之前的面向对象的学习可知，final
 *    修饰的属性必须在对象创建时就赋上值。所以：枚举类的构造器中，必须给
 *    所有被final修饰的属性赋上值。如果某个构造器没有给某个final修饰的属性赋上值，就会
 *    报错。     其次，被final修饰的属性不能有set方法，因为被final修饰的属性不能变。
 *
 * 二、枚举类的使用场景
 * 1.当需要定义一组常量时，强烈建议使用枚举类
 *
 *
 * 三、如何定义枚举类
 * 方式一：自定义枚举类
 * 方式二：使用enum关键字定义枚举类
 *
 * 方式二是jdk5.0新增的。
 *
 * 四、
 * JDK 1.5 中可以在 switch 表达式中使用Enum定义的枚举类的对象
 * 作为表达式, case 子句可以直接使用枚举值的名字, 无需添加枚举
 * 类作为限定
 *
 * @author shkstart
 * @create 2019 上午 10:17
 */
public class SeasonTest {

    public static void main(String[] args) {
        Season spring = Season.SPRING;
        System.out.println(spring);

    }

}

//自定义枚举类
class Season{
    //1.声明Season对象的属性:private final修饰
    private final String seasonName;
    private final String seasonDesc;

    //2.私有化类的构造器,并给对象属性赋值
    private Season(String seasonName,String seasonDesc){
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    //3.提供当前枚举类的多个对象：public static final的
    public static final Season SPRING = new Season("春天","春暖花开");
    public static final Season SUMMER = new Season("夏天","夏日炎炎");
    public static final Season AUTUMN = new Season("秋天","秋高气爽");
    public static final Season WINTER = new Season("冬天","冰天雪地");

    //4.其他诉求1：获取枚举类对象的属性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }
    //4.其他诉求1：提供toString()
    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}
