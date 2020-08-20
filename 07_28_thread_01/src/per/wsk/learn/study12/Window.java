package per.wsk.learn.study12;

/**
 * @Author weishaokang
 * @date 2020-07-28 11:44
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class Window extends Thread{

    public static int ticket = 100;
    public static int times = 1;
    public static boolean isSaleTicket = true;
    @Override
    public void run() {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        saleTicket();
    }


    private static synchronized void saleTicket (){
        //此时的同步监视器是：Window4.class
        if (ticket>0) {
            System.out.println("第"+ times++ +"个线程进来\t"+ticket+"\t");
            ticket--;
        }
    }

}
