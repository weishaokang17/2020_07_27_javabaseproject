package per.wsk.learn.study10.$02;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author weishaokang
 * @date 2020-07-29 18:04
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class CallableTest {

    /*
    ① 先创建一个类，实现Callable接口，重写call()方法，在call()方法里面写异步的业务逻辑
    ② 新建一个上面Callable接口实现类的对象
    ③ 新建FutureTask对象，FutureTask有一个带Callable类型参数的构造器，用这个构造器，
       Callable类型的参数就选择第②步创建的对象
    ④ 新建一个Thread对象，Thread有一个带Runnable类型参数的构造器，用这个构造器，
       Runnable类型的参数就选择第③步创建的FutureTask对象（因为FutureTask也实现了Runnable接口）
    ⑤ 执行线程，第④步的Thread对象.start()方法
    ⑥ 第③步的FutureTask对象.get()获取返回值

     */

    public static void main(String[] args) {
        ThreadArray threadArray = new ThreadArray();

        //FutureTask类中有一个Callable类型的属性，同时FutureTask有一个带Callable类型参数的构造器，
        // 用这个构造器创建FutureTask对象，就直接给当前FutureTask对象的Callable属性赋上了值
        FutureTask<ArrayList<Integer>> futureTask = new FutureTask<>(threadArray);

        //新建一个Thread对象，Thread有一个带Runnable类型参数的构造器，用这个构造器，
        // 新建Thread对象，就直接给当前Thread对象的Runnable类型的属性赋上了值
        // （futureTask实现了Runnable接口，故futureTask对象也属于Runnable类型）
        Thread thread = new Thread(futureTask);

        //调用Thread类的start()方法，执行的是上面futureTask的run()方法，
        //futureTask的run()方法里面执行的是当前futureTask对象的callable属性中的call方法
        //由于futureTask对象中的callable属性的值 是threadArray这个类，所以执行的是
        //threadArray对象中的call()方法
        thread.start();


        ArrayList<Integer> resulltList = null;
        try {
            resulltList = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            System.out.println(resulltList);
        }

        System.out.println("主线程结束");
    }
}
