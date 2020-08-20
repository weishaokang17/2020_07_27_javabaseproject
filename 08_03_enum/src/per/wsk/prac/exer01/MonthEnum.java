package per.wsk.prac.exer01;

/**
 * @Author weishaokang
 * @date 2020-08-04 17:01
 * @project 2020_07_27_javabaseproject
 * @description:
 * 创建月份枚举类，枚举值包含十二个月份，月份要求用英文单词
 */
public enum  MonthEnum {

    JANUARY(1,"JANUARY"),
    FEBRUARY(2,"FEBRUARY"),
    MARCH(3,"MARCH"),
    APRIL(4,"APRIL"),
    MAY(5,"MAY"),
    JUNE(6,"JUNE"),
    JULY(7,"JULY"),
    AUGUST(8,"AUGUST"),
    SEPTEMBER(9,"SEPTEMBER"),
    OCTOBER(10,"OCTOBER"),
    NOVEMBER(11,"NOVEMBER"),
    DECEMBER(12,"DECEMBER"),
    ;

    private final int monthNum;
    private final String monthName;

    private MonthEnum(int monthNum, String monthName) {
        this.monthNum = monthNum;
        this.monthName = monthName;
    }

    public int getMonthNum() {
        return monthNum;
    }

    public String getMonthName() {
        return monthName;
    }
}
