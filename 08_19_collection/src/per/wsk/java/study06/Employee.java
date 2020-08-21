package per.wsk.java.study06;

import java.util.Objects;

/**
 * @Author weishaokang
 * @date 2020-08-21 12:09
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class Employee implements Comparable{

    private String name;
    private int age;
    private MyDate birthday;

    public Employee(String name, int age, MyDate birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
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

    public MyDate getBirthday() {
        return birthday;
    }

    public void setBirthday(MyDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }


    @Override
    public int compareTo(Object o) {
        if (o instanceof Employee) {
            return this.name.compareTo(((Employee) o).name);
        } else {
            throw new RuntimeException("对象类型错误");
        }
    }
}
