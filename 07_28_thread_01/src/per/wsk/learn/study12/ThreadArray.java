package per.wsk.learn.study12;

import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 * @Author weishaokang
 * @date 2020-07-29 18:09
 * @project 2020_07_27_javabaseproject
 * @description:
 */
public class ThreadArray implements Callable<ArrayList<Integer>> {

    /**
     * 获取1000以内的所有质数
     * @return
     * @throws Exception
     */
    @Override
    public synchronized ArrayList<Integer> call() throws Exception {
        ArrayList<Integer> resultList = new ArrayList<>();
        Thread.currentThread().setName("返回数组的线程");
        resultList.add(2);
        for (int i = 3; i <= 1000; i++) {
            int count = 0;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i%j==0) {
                    count++;
                    break;
                }
            }
            if (count==0) {
                resultList.add(i);
            }
        }
//        Thread.sleep(5000l);
//        this.wait();
        //经过尝试说明：主线程如果不去获取分线程的返回值，那么此时主线程不必等分线程结束才执行
        //主线程自己后面的代码。 但如果主线程要获取分线程的返回值，那么此时主线程
        //就必须等到分线程结束，才能执行获取完返回值后面的代码。如果分线程中间睡了，或者
        //wait()一直不被唤醒，那么主线程会一直等待，直到分线程运行结束。
        System.out.println(Thread.currentThread().getName());
        return resultList;
    }
}
