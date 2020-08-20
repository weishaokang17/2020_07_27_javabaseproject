package per.wsk.learn.study04;

/**
 * @Author weishaokang
 * @date 2020-07-28 11:44
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class Window extends Thread{

    public static int ticket = 100;
    public static boolean isSaleTicket = true;
    @Override
    public void run() {
        while (isSaleTicket) {
            saleTicket();
        }
    }


    private static synchronized void saleTicket (){
        //此时的同步监视器是：Window4.class
        if (ticket>0) {
            System.out.println(ticket);
            ticket--;
        }  else {
            isSaleTicket = false;
        }
    }

}
