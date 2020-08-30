package per.wsk.exer.prac01;

/**
 * @Author weishaokang
 * @date 2020-08-29 23:50
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class Student {

    private String name;
    public int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student() {
    }

    private Student(String name) {
        System.out.println("私有的构造器");
        this.name = name;
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
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void show(){
        System.out.println("公共的show()方法");
    }

    private String showNation(String nation){
        System.out.println("私有的showNation()方法");
        return nation;
    }

}
