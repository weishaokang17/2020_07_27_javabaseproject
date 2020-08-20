package per.wsk.prac.exer02;

/**
 * @Author weishaokang
 * @date 2020-08-04 17:10
 * @project 2020_07_27_javabaseproject
 * @description:
 *
 * 创建星期枚举类，有7个枚举值，包含计划属性plan，为此属性赋值（使用构造器）。
 */
public enum Weekend {

    MONDAY("plan1"),
    TUESDAY("plan2"),
    WEDNESDAY("plan3"),
    THURSDAY("plan4"),
    FRIDAY("plan5"),
    SATURDAY("plan6"),
    SUNDAY("plan7");

    private final String plan;

    private Weekend(String plan) {
        this.plan = plan;
    }
}
