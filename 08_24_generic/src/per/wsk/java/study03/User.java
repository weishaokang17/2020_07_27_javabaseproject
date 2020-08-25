package per.wsk.java.study03;

/**
 * @Author weishaokang
 * @date 2020-08-24 16:18
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class User<T> {
    private T name;

    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }
}
