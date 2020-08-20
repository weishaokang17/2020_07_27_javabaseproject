package per.wsk.prac.$03;

/**
 * @Author weishaokang
 * @date 2020-07-30 11:25
 * @project 2020_07_27_javabaseproject
 * @description:
 *
 *
 * 实现一个由A、B、C三个窗口同时销售100张票的系统，要求打印出每个窗口打印的售票情况，
 * 并且每个窗口不得连续售票。
 */
public class ThreadTest {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(ticket,"窗口一").start();
        new Thread(ticket,"窗口二").start();
        new Thread(ticket,"窗口三").start();

    }
}
