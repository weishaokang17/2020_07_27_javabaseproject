package per.wsk.exer.prac02;

/**
 * @Author weishaokang
 * @date 2020-09-02 23:17
 * @project 2020_07_27_javabaseproject
 * @description:
 *
 *      //交易员类
 */
public class Trader {

    private String name;
    private String city;

    public Trader() {

    }
    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Trader [name=" + name + ", city=" + city + "]";
    }

}
