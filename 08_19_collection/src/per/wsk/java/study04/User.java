package per.wsk.java.study04;

/**
 * @author shkstart
 * @create 2019 下午 3:56
 */
public class User implements Comparable{
    private String name;
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("User equals()....");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (age != user.age) return false;
        return name != null ? name.equals(user.name) : user.name == null;
    }

    /*
    重写 hashCode() 方法的基本原则
    （这些原则结合set集合添加元素的底层原理，就能明白为什么要有这3个原则了）
    ①在程序运行时，同一个对象多次调用 hashCode() 方法应该返回相同的值。
    ②当两个对象的 equals() 方法比较返回 true 时，这两个对象的 hashCode()
        方法的返回值也应相等。
    ③对象中用作 equals() 方法比较的 Field，都应该用来计算 hashCode 值。
     */

    //结论：复写equals方法的时候一般都需要同时复写hashCode方法。
    // 通常参与计算hashCode的对象的属性也应该参与到equals()中进行计算。
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }

    /*
    以Eclipse/IDEA为例，在自定义类中可以调用工具自动重写equals和hashCode。
    问题：为什么用Eclipse/IDEA复写hashCode方法，有31这个数字？

     选择系数的时候要选择尽量大的系数。因为如果计算出来的hash地址越大，所谓的
    “冲突”就越少，查找起来效率也会提高。（减少冲突，冲突其实就是hash值相同）
     并且31只占用5bits,相乘造成数据溢出的概率较小。
     31可以 由i*31== (i<<5)-1来表示,现在很多虚拟机里面都有做相关优化。（提高算法效
    率）
     31是一个素数，素数作用就是如果我用一个数字来乘以这个素数，那么最终出来的结
    果只能被素数本身和被乘数还有1来整除！(减少冲突)
     */



    //按照姓名从大到小排列,年龄从小到大排列
    @Override
    public int compareTo(Object o) {
        if(o instanceof User){
            User user = (User)o;
//            return -this.name.compareTo(user.name);
            int compare = -this.name.compareTo(user.name);
            if(compare != 0){
                return compare;
            }else{
                return Integer.compare(this.age,user.age);
            }
        }else{
            throw new RuntimeException("输入的类型不匹配");
        }

    }
}
