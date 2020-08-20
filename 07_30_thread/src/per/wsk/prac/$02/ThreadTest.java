package per.wsk.prac.$02;

/**
 * @Author weishaokang
 * @date 2020-07-30 10:40
 * @project 2020_07_27_javabaseproject
 * @description:
 *
 * 启动两个线程对一个数字i操作
 *
 * 1)其中1个线程每次对i加1
 * 2)另1个线程每次对i减1
 *
 * 各运行20次，结果i的值等于初始值。
 */
public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        final int[] i = {100};

        Object obj = new Object();

        /*new Thread(){
            @Override
            public void run() {
                for (int j = 1; j < 21; j++) {
                    synchronized (obj) {
                        obj.notify();
                        i[0]++;
                        System.out.println("第"+j+"次把i[0]加1");

                        if(j==20){
                            break;
                        }

                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                for (int j = 1; j < 21; j++) {
                    synchronized (obj) {
                        obj.notify();
                        i[0]++;
                        System.out.println("第"+j+"次把i[0]减1");

                        if(j==20){
                            break;
                        }

                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();*/


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 1; j < 21; j++) {
                    synchronized (obj) {
                        obj.notify();
                        i[0]++;
                        System.out.println("第"+j+"次把i[0]加1");

                        if(j==20){
                            break;
                        }

                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 1; j < 21; j++) {
                    synchronized (obj) {
                        obj.notify();
                        i[0]--;
                        System.out.println("第"+j+"次把i[0]减1");

                        if(j==20){
                            break;
                        }

                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();


        Thread.sleep(2000L);
        System.out.println("最终i[0]的值是"+i[0]);
    }
}
