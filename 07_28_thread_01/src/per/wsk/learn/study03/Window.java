package per.wsk.learn.study03;

/**
 * @Author weishaokang
 * @date 2020-07-28 11:36
 * @project 2020_07_27_javabaseproject
 * @description:
 */




/**
 * 使用同步方法解决实现Runnable接口的线程安全问题
 *
 *
 *  关于同步方法的总结：
 *  1. 同步方法仍然涉及到同步监视器，只是不需要我们显式的声明。
 *  2. 非静态的同步方法，同步监视器是：this
 *     静态的同步方法，同步监视器是：当前类本身
 */
public class Window implements Runnable{

    private int ticket = 100;
    private boolean isSaleTicket = true;
    @Override
    public void run() {
        while (isSaleTicket){
            saleTicket();
        }
    }

    private synchronized void saleTicket(){//此时的同步监视器是：this
        if (this.ticket > 0) {
            System.out.println(this.ticket);
            this.ticket--;
        } else {
            isSaleTicket = false;
        }
    }
}
