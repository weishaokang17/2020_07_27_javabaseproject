package per.wsk.prac.exer02;

/**
 * @Author weishaokang
 * @date 2020-07-28 20:44
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class ThreadTest {
    public static void main(String[] args) {
        Account account = new Account("1144", 10000);

        XiaoMing xiaoMing = new XiaoMing("小明");
        XiaoMingWife xiaoMingWife = new XiaoMingWife("小明女朋友");

        xiaoMing.setAccount(account);
        xiaoMingWife.setAccount(account);

        xiaoMing.setMoney(2000);
        xiaoMingWife.setMoney(1500);

        xiaoMing.start();
        xiaoMingWife.start();

    }
}
