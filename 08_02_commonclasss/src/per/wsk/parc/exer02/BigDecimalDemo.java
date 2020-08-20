package per.wsk.parc.exer02;

import java.math.BigDecimal;

/**
 * @Author weishaokang
 * @date 2020-08-03 19:53
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class BigDecimalDemo {
    //除法保留小数点后10位
    static final int location = 10;


    public static void main(String[] args) {
        BigDecimalDemo b = new BigDecimalDemo();
        System.out.println("两个数字相加结果：" + b.add(-7.5, 8.9));
        System.out.println("两个数字相减结果：" + b.sub(-7.5, 8.9));
        System.out.println("两个数字相乘结果：" + b.mul(-7.5, 8.9));
        System.out.println("两个数字相除结果，结果小数后保留10位：" + b.div(10, 2));
        System.out.println("两个数字相除，结果保留小数后5位：" + b.div(-7.5, 8.9, 5));
    }

    /*
    加
     */
    public static BigDecimal add(double value1, double value2) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.add(b2);
    }

    /*
    减
     */
    public static BigDecimal sub(double value1, double value2) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.subtract(b2);
    }

    /*
    乘
     */
    public static BigDecimal mul(double value1, double value2) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.multiply(b2);
    }

    /*
       除
     * 提供精确的除法运算当除不尽时，精确到小数点后10位，以后的数字四舍五入
     */
    public static BigDecimal div(double value1, double value2) {
        return div(value1, value2, location);
    }

    /*
     * @param b 表示需要精确到小数点以后的位数
     */
    public static BigDecimal div(double value1, double value2, int b) {
        if (b < 0) {
            System.out.println("b值必须大于等于0");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.divide(b2, b, BigDecimal.ROUND_HALF_UP);
    }


}
