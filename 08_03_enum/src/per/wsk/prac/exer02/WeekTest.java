package per.wsk.prac.exer02;

/**
 * @Author weishaokang
 * @date 2020-08-04 17:25
 * @project 2020_07_27_javabaseproject
 * @description:
 * 在main方法中从命令行接收一个1-7的整数(使用Integer.parseInt方法转换)，
 * 分别代表星期一至星期日，打印该值对应的枚举值，
 * 然后以此枚举值调用printWeek方法，输出中文星期。
 */
public class WeekTest {

    public static void main(String[] args) {
        int value = Integer.parseInt(args[0]);
        Weekend weekend = null;
        switch (value) {
            case 1:
                weekend = Weekend.MONDAY;
                break;
            case 2:
                weekend = Weekend.TUESDAY;
                break;
            case 3:
                weekend = Weekend.WEDNESDAY;
                break;
            case 4:
                weekend = Weekend.THURSDAY;
                break;
            case 5:
                weekend = Weekend.FRIDAY;
                break;
            case 6:
                weekend = Weekend.SATURDAY;
                break;
            case 7:
                weekend = Weekend.SUNDAY;
                break;
        }

        printWeek(weekend);
    }

    private static void printWeek(Weekend weekend){
        switch (weekend){
            case MONDAY:
                System.out.println("星期一");
                break;
            case TUESDAY:
                System.out.println("星期二");
                break;
            case WEDNESDAY:
                System.out.println("星期三");
                break;
            case THURSDAY:
                System.out.println("星期四");
                break;
            case FRIDAY:
                System.out.println("星期五");
                break;
            case SATURDAY:
                System.out.println("星期六");
                break;
            case SUNDAY:
                System.out.println("星期天");
                break;
        }
    }
}
