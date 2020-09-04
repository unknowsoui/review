package Thread_senior_study;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class ThreadLocalTest {
    private static ThreadLocal<String> HOLDER = new ThreadLocal<>();

    @Test
    public void t1() throws InterruptedException {

        //ThreadLocal对象都和线程绑定，里面的值每个线程间是隔离开的
//        HOLDER.get();//获取当前线程中，某个ThreadLocal对象的值
//        HOLDER.remove();//删除当前线程中，某个ThreadLocal对象的值
//        HOLDER.set("abc");//设置当前线程中，某个ThreadLocal对象的值
        CountDownLatch countDownLatch = new CountDownLatch(20);
        for(int i = 0;i < 20;i++){
            final int k = i;
            new Thread(() ->{
                try {
                    HOLDER.set(String.valueOf(k));
                    if(k == 5){
                        Thread.sleep(500);
                        System.out.println(HOLDER.get());
                    }
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    HOLDER.remove();
                }
            }).start();
        }
        countDownLatch.await();
        System.out.println(HOLDER.get());
        HOLDER.remove();//只要在某个线程设置了ThreadLocal值，在线程结束之前，一定要remove；
    }
}
