package Thread_senior_study;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class ThreadWaitThenDo {

    /**
     * yield()阻塞
     */
    @Test
    public void t1(){
        for(int i = 0;i < 20;i++){
            new Thread(() ->
                    System.out.println(Thread.currentThread().getName())
            ).start();
        }
        //入口线程执行t1（）方法，入口线程阻塞等待，直到所有子线程执行完毕
        while (Thread.activeCount() > 1){
            Thread.yield();
        }
        System.out.println("执行完毕 ：" + Thread.currentThread().getName());
    }

    /**
     * join阻塞
     * @throws InterruptedException
     */
    @Test
    public void t2() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for(int i = 0;i < 20;i++){
            Thread thread = new Thread(() ->
                    System.out.println(Thread.currentThread().getName())
            );
            threads.add(thread);
            thread.start();
        }
        for(Thread thread : threads){
            thread.join();
        }
        System.out.println("执行完毕 ：" + Thread.currentThread().getName());
    }

    /**
     * CountDownLatch阻塞
     * @throws InterruptedException
     */
    @Test
    public void t3() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(20);
        for(int i = 0;i < 20;i++){
            Thread thread = new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
                countDownLatch.countDown();//计数器值-1
            });
            thread.start();
        }
        countDownLatch.await();//当前线程阻塞等待，直到计数器的值=0
        System.out.println("执行完毕 ：" + Thread.currentThread().getName());
    }

    /**
     * Semaphore阻塞 应用一
     * @throws InterruptedException
     */
    @Test
    public void t4() throws InterruptedException {
        //阻塞等待一组线程执行完毕，再执行任务
        Semaphore semaphore = new Semaphore(0);
        for(int i = 0;i < 20;i++){
            Thread thread = new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
                semaphore.release();
            });
            thread.start();
        }
        semaphore.acquire(20);
        System.out.println("执行完毕 ：" + Thread.currentThread().getName());
    }

    /**
     * Semaphore 应用二
     * @throws InterruptedException
     */
    @Test
    public void t5() throws InterruptedException {
        //模拟服务器接收客户端http请求：只有1000个并发
        //（在一个时间点，客户端任务达到1000，再有客户端请求，将阻塞等待）
        Semaphore semaphore = new Semaphore(1000);
        for(;;){
            Thread thread = new Thread(() -> {
                try {
                    semaphore.acquire();
                    //模拟每个线程处理客户端http请求
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {

    }
}
