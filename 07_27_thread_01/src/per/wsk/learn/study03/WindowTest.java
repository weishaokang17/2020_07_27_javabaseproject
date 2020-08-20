package per.wsk.learn.study03;

/**
 *
 * 例子：创建三个窗口卖票，总票数为100张.使用继承Thread类的方式
 *
 * 存在线程的安全问题，待解决。
 *
 * @author shkstart
 * @create 2019-02-13 下午 4:20
 */
class Window extends Thread{

    //此处ticket必须声明成static，如果去掉static，相当于每个Window对象独立的拥有100张票
    private static int ticket = 100;
    @Override
    public void run() {

        while(true){

            if(ticket > 0){
                System.out.println(getName() + "：卖票，票号为：" + ticket);
                ticket--;
            }else{
                break;
            }
        }
    }

    //构造器
    public Window(String name) {
        super(name);
    }
}


public class WindowTest {
    public static void main(String[] args) {
        Window t1 = new Window("窗口1");
        Window t2 = new Window("窗口2");
        Window t3 = new Window("窗口3");

        t1.start();
        t2.start();
        t3.start();

    }
}
