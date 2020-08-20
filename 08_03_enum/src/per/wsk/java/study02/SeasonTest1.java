package per.wsk.java.study02;

/**
 * 使用enum关键字定义枚举类
 * 说明：定义的枚举类默认继承于java.lang.Enum类

   一、枚举类Enum
      jdk5.0之前只能自己定义枚举类，jdk5.0开始java新增了Enum来定义枚举类，
      Enum枚举类相比较自定义的枚举类，除了要满足SeasonTest中说明的自定义枚举类的两个条件，
      还必须满足下面的几个条件：
      ①枚举类继承了java.lang.Enum，java.lang.Enum又继承了Object,所以枚举类也间接继承了Object,
        但因为枚举类继承了java.lang.Enum，所以不能让枚举类继承某个别的类。因为java中类都是
        单继承的
      ②枚举类中的有限个对象，前面都是public static final修饰的，在枚举类中，这些有限个对象前面
        的public static final 修饰符必须省略掉，不省略会报错，同时不能再加其他修饰符。
        由于这些对象都被public static final修饰，故都是常量，所以这些对象的名称按照规范都大写。
      ③java用构造器创建对象时，都是用  new 类名(构造器参数列表);
      枚举类中的这些有限个对象在创建时，后面的构造器，必须省略 new 类名，只写(构造器参数列表)，
      如果这个枚举类没有final修饰的属性，创建对象时可以使用空参构造器，则只写 对象名，
      对象名后面的 new 类名()全部省略，可以参照Thread类的内部枚举State。
      ④同时这些有限个对象，中间必须用逗号,间隔，最后一个对象后面用分号;表示结束
      ⑤枚举类的所有对象必须在枚举类的第一行也就是最前面声明，即枚举类的有限个对象不能放在
       构造器及其他方法后面，必须放在最前面。

   二、Enum类中的常用方法：
 *    ①values()方法：返回枚举类型的对象数组。该方法可以很方便地遍历所有的枚举值。
 *    ②valueOf(String str)：可以把一个字符串转为对应的枚举类对象。要求字符串必须是枚举类对象的“名字”。如不是，会有运行时异常：IllegalArgumentException。
 *    ③toString()：返回当前枚举类对象常量的名称
 *
 * 三、使用enum关键字定义的枚举类实现接口的情况
 *
 *   枚举类除了上面的说明外，和普通类并没有什么分别，例如枚举类也可以像正常类一样实现接口，
 *   也可以有内部枚举类等。
 *
 *   当枚举类实现接口时，由接口的定义可知，枚举类必须实现接口中的抽象方法，此时枚举类实现接口
 *   中的抽象方法有两种方式
 *
 *   方式一：在enum类中实现抽象方法
 *   方式二：由于枚举类只有有限个对象，可以不在类中实现接口的抽象方法，
 *           可以让枚举类的每个对象分别实现接口中的抽象方法
 *
 *   这两个方式详见下面的说明
 * @author shkstart
 * @create 2019 上午 10:35
 */
public class SeasonTest1 {
    public static void main(String[] args) {


        System.out.println(Season1.class.getSuperclass());
        //上面这行输出结果是：class java.lang.Enum   说明枚举类的父类是java.lang.Enum
        Class<? super Enum> superclass = Enum.class.getSuperclass();
        System.out.println(superclass);
        //上面这行输出：class java.lang.Object   说明java.lang.Enum的父类是Object,
        //说明枚举类也间接继承了Object

        System.out.println("****************");

        Season1 summer = Season1.SUMMER;
        //toString():返回枚举类对象的名称，因为枚举类继承了java.lang.Enum,java.lang.Enum
        //又继承了Object,但java.lang.Enum重写了Object中的toString方法，故下面这行输出的不是
        //summer这个对象的地址值。
        System.out.println(summer.toString());//输出 SUMMER  这个字符串，不是对象的地址值

        //values():返回所有的枚举类对象构成的数组
        Season1[] values = Season1.values();
        for(int i = 0;i < values.length;i++){
            System.out.println(values[i]);
            values[i].show();
        }
        System.out.println("****************");
        Thread.State[] values1 = Thread.State.values();
        for (int i = 0; i < values1.length; i++) {
            System.out.println(values1[i]);
        }

        //valueOf(String objName):返回枚举类中对象名是objName的对象。
        Season1 winter = Season1.valueOf("WINTER");
        //如果没有objName的枚举类对象，则抛异常：IllegalArgumentException，例如下面这行，
        //因为Season1这个枚举类中不存在WINTER1这个对象。
//        Season1 winter = Season1.valueOf("WINTER1");
        System.out.println(winter);
        winter.show();


        /*
        获取枚举类中某个对象有两种方式
        ①由于枚举类的对象都是public static final 修饰，
        所以可以用  枚举类名.对象名 获取这个对象。 例如Season1.AUTUMN。
        ②用valueof方法，如下面两行代码所示。
         */
        Season1 autumn = Season1.AUTUMN;
        Season1 spring = Season1.valueOf("SPRING");
    }
}

interface Info{
    void show();
}

//使用enum关键字枚举类
enum Season1 implements Info {
    //1.提供当前枚举类的对象，多个对象之间用","隔开，末尾对象";"结束
    SPRING("春天","春暖花开",""){
        @Override
        public void show() {
            System.out.println("春天在哪里？");
        }
    },
    SUMMER("夏天","夏日炎炎",""){
        @Override
        public void show() {
            System.out.println("宁夏");
        }
    },
    AUTUMN("秋天","秋高气爽",""){
        @Override
        public void show() {
            System.out.println("秋天不回来");
        }
    },
    WINTER("冬天","冰天雪地",""){
        @Override
        public void show() {
            System.out.println("大约在冬季");
        }
    };

    //2.声明Season对象的属性:private final修饰
    private final String seasonName;
    private final String seasonDesc;
    private final String seasonTime;
    //3.私有化类的构造器,并给对象属性赋值

//    private Season1(String seasonName,String seasonDesc){
//        this.seasonName = seasonName;
//        this.seasonDesc = seasonDesc;
//    }

    private Season1(String seasonName, String seasonDesc, String seasonTime) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
        this.seasonTime = seasonTime;
    }


    public String getSeasonTime() {
        return seasonTime;
    }

    //4.其他诉求1：获取枚举类对象的属性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }
//    //4.其他诉求1：提供toString()
//
//    @Override
//    public String toString() {
//        return "Season1{" +
//                "seasonName='" + seasonName + '\'' +
//                ", seasonDesc='" + seasonDesc + '\'' +
//                '}';
//    }


//    @Override
//    public void show() {
//        System.out.println("这是一个季节");
//    }
}