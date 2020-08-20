package per.wsk.learn.study02;

/**
 * @Author weishaokang
 * @date 2020-07-28 11:06
 * @project 2020_07_27_javabaseproject
 * @description:
 */


/**
 * 使用同步代码块解决继承Thread类的方式的线程安全问题
 *
 * 例子：创建三个窗口卖票，总票数为100张.使用继承Thread类的方式
 *
 * 说明：在继承Thread类创建多线程的方式中，慎用this充当同步监视器，
 * 考虑使用当前类充当同步监视器。
 *
 */
public class Window extends Thread{


    private static int ticket = 100;

    private static Object obj = new Object();

    @Override
    public void run() {

        while(true){
            //下面这两行都是正确的   (此时obj对象必须声明是static修饰，否则不满足同步监视器的要求)
//            synchronized (obj){
            synchronized (Window.class){
                //Class clazz = Window2.class,Window2.class只会加载一次，这个后面讲的反射
                //时会详细讲到

                //下面这行是错误的方式：this代表着t1,t2,t3三个对象，不满足同步监视器的要求
//              synchronized (this){

                if(ticket > 0){

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(getName() + "：卖票，票号为：" + ticket);
                    ticket--;
                }else{
                    break;
                }
            }

        }

    }

    public Window(String name){
        super(name);
    }
}
