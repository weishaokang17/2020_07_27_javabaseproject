package per.wsk.exer.prac01;

/**
 * @Author weishaokang
 * @date 2020-08-04 17:40
 * @project 2020_07_27_javabaseproject
 * @description:
 */

public class Person {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @MyTiger(value="fuck you",arr={1,2,3,4})
    public String getName() {
        return name;
    }

    @MyTiger(value="I am your father",arr={14,2,3,24})
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
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
