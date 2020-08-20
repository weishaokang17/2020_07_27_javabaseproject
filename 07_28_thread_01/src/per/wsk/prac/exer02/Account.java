package per.wsk.prac.exer02;

/**
 * @Author weishaokang
 * @date 2020-07-28 20:40
 * @project 2020_07_27_javabaseproject
 * @description:
 *
 *
 * .定义一个Account类
 * 1）该Account类封装了账户编号（String）和余额（double）两个属性
 * 2）设置相应属性的getter和setter方法
 * 3）提供无参和有两个参数的构造器
 * 4）系统根据账号判断与用户是否匹配，需提供hashCode()和equals()方法的重写
 *
 * 2.提供两个取钱的线程类：小明、小明’s wife
 * 1）提供了Account类的account属性和double类的取款额的属性
 * 2）提供带线程名的构造器
 * 3）run()方法中提供取钱的操作
 *
 * 3.在主类中创建线程进行测试。考虑线程安全问题。
 */
public class Account {
    //账户编号
    private String id ;
    //余额
    private double balance;

    public Account() {
    }

    public Account(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    //取钱
    public void withdrawMoney(double money){
        synchronized (this) {
            if (this.balance>money) {
                this.balance-=money;
                System.out.println(Thread.currentThread().getName() + " 成功取出" + money + "元");
            } else {
                System.out.println("余额不足,"+Thread.currentThread().getName()+
                        "取款失败");
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
