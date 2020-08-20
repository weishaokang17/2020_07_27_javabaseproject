package per.wsk.prac.$04;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author weishaokang
 * @date 2020-07-30 11:38
 * @project 2020_07_27_javabaseproject
 * @description:
 *
 * 模拟3个人排队买票，每人买1张票。售货员只有1张五元的钱，电影票5元一张，
 * 王大拿拿一张二十元的人民币排在谢大脚前面买票，
 * 谢大脚拿1张十元的人民币排在在赵四的前面买票，
 * 赵四拿1张五元的人民币排在最后。
 *
 * 即最终的卖票次序是：谢大脚、赵四、王大拿
 */
public class ThreadTest {

    //创建线程池
    public static ThreadPoolExecutor threadPool= new ThreadPoolExecutor(
            5,
            10,
            60,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(10),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.CallerRunsPolicy());


    public static void main(String[] args) {
        //依次排队买票
/*        PersonThread thread1 = new PersonThread("王大拿", 20);
        PersonThread thread2 = new PersonThread("谢大脚", 10);
        PersonThread thread3 = new PersonThread("赵四", 5);

        thread1.setPriority(Thread.MAX_PRIORITY);
        thread2.setPriority(Thread.NORM_PRIORITY);
        thread3.setPriority(Thread.MIN_PRIORITY);

        thread1.start();
        thread2.start();
        thread3.start();*/

        //方式二：使用线程池
        PersonThread thread1 = new PersonThread("王大拿", 20,10);
        PersonThread thread2 = new PersonThread("谢大脚", 10,5);
        PersonThread thread3 = new PersonThread("赵四", 5,1);

        threadPool.execute(thread1);
        threadPool.execute(thread2);
        threadPool.execute(thread3);
        //关闭线程池
        threadPool.shutdown();
    }
}



class PersonThread extends Thread{


    private String userName;//姓名
    private double balance;//余额
    private int priority;//线程优先级

    private static Ticket ticket = new Ticket();//窗口余额

    @Override
    public void run() {
        Thread.currentThread().setPriority(this.priority);
        synchronized (ticket) {
            //下面的代码最开始写的是if,改成while就满足题意了
            while (this.balance-5 > ticket.getBalance()) {
                try {
                    ticket.wait();//此处去掉ticket.会报错，因为会默认是this.，调用wait必须用同步监视器
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.balance-=5;
            ticket.setBalance(ticket.getBalance()+5);
            System.out.println(this.getUserName()+"买到了票，他现在的余额是"+balance+" 元," +
                    "现在售票处的余额是 "+ticket.getBalance()+" 元");

            ticket.notify();
        }
    }

    public PersonThread(String userName,double balance,int priority){
        super();
        this.userName = userName;
        this.balance = balance;
        this.priority = priority;
    }

    public PersonThread(String userName,double balance){
        super();
        this.userName = userName;
        this.balance = balance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}


class Ticket {
    private double balance = 5;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
