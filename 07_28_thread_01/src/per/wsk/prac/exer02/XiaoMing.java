package per.wsk.prac.exer02;

/**
 * @Author weishaokang
 * @date 2020-07-28 20:43
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class XiaoMing extends Thread{

    private Account account;//银行账户

    private double money;//取款额

    @Override
    public void run() {
        this.account.withdrawMoney(this.money);
    }

    //带线程名的构造器
    public XiaoMing(String name) {
        super(name);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
