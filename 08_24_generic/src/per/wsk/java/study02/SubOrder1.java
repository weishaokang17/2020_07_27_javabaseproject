package per.wsk.java.study02;

/**
 * @author shkstart
 * @create 2019 上午 11:18
 */
public class SubOrder1<T> extends Order<Integer> {//SubOrder1<T>:仍然是泛型类
    //下面这行代码，写法会报错，因为继承的父类对象的泛型需要指定，但子类没有泛型，
    //这样，新建子类对象时无法指定父类的泛型。
    //    public class SubOrder1 extends Order<T> {
        private T name;

    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }
}
