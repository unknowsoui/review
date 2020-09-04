package Thread_study;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;


/**
 * 定时器：
 * 1.在约定好的时间点上，执行某个任务
 * 2.间隔时间到了，不停的执行任务
 */
public class MyTimer {
    private BlockingQueue<MyTimerTask> queue = new PriorityBlockingQueue<>();

    /**
     * 在创建对象的时候就创建线程来执行
     */
    public MyTimer() {
        new Thread(new MyWork(queue)).start();
    }

    /**
     * 实现的执行方法
     */
    private static class MyWork implements Runnable {
        BlockingQueue<MyTimerTask> queue;

        public MyWork(BlockingQueue<MyTimerTask> queue){
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    MyTimerTask task = queue.take();
                    synchronized (queue) {
                        long current = System.currentTimeMillis();
                        if (task.next > current) {
                            queue.wait(task.next - current);
                            queue.put(task);
                        } else {
                            task.task.run();
                            if (task.period > 0) {
                                task.next += task.period;
                                queue.put(task);
                            }
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 自定义定时器类，重写compareTo
     */
    private static class MyTimerTask implements Comparable<MyTimerTask> {
        private Runnable task;
        private long next;
        private long period;

        public MyTimerTask(Runnable task, long delay, long period) {
            this.task = task;
            this.next = delay;
            this.period = period;
        }

        @Override
        public int compareTo(MyTimerTask o) {
            return Long.compare(o.next, this.next);
        }
    }

    /**
     * 自己定义的定时器
     *
     * @param task   需要执行的任务
     * @param delay  从当前时间延迟多少毫秒，执行任务
     * @param period 间隔时间 《=0就忽略 》0就需要根据间隔时间执行任务
     */
    public void schedule(Runnable task, long delay, long period) {
        try {
            queue.put(new MyTimerTask(task, System.currentTimeMillis() + delay, period));
            synchronized (queue) {
                queue.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //jdk 的时间操作
    public static void main(String[] args) {
        //Date
        Date date = new Date();// 无参构造方法，直接返回系统当前时间
        Date date1 = new Date(123);//以格林威治时间1970-01-01开始，经过给定时间数量的毫秒
        //DateFormat 按照给定格式返回
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //System时间获取 从1970-01-01到当前时间点经过的毫秒数
        long current = System.currentTimeMillis();
        MyTimer myTimer = new MyTimer();
        myTimer.schedule(new Runnable() {
                             @Override
                             public void run() {
                                 System.out.println("hh");
                             }
                         },
                1000,
                100);
    }
}
