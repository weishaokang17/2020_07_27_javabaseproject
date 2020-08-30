package per.wsk.java.study06;

/**
 * @Author weishaokang
 * @date 2020-08-30 23:25
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class Car {

    private String name;
    private double price;
    private String color;
    private double lifetime;//寿命

    public Car() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getLifetime() {
        return lifetime;
    }

    public void setLifetime(double lifetime) {
        this.lifetime = lifetime;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", color='" + color + '\'' +
                ", lifetime=" + lifetime +
                '}';
    }
}
