package Thread_senior_study;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * callable创建线程
 * Future / FutureTask
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> c = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("call");
                return null;
            }
        };
        FutureTask<Integer> task = new FutureTask<>(c);
        new Thread(task).start();
        System.out.println("main");
        Integer r = task.get();//当前线程阻塞等待，直到线程执行完（类似于join），但是可以获取线程的返回值
        System.out.println(r);
    }
}
