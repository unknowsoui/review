package Thread_study;

import java.util.concurrent.*;

public class Threadpools_study {
    ExecutorService pool1 = Executors.newSingleThreadExecutor(); //线程池所拥有的线程只有一个
    ExecutorService pool2 = Executors.newFixedThreadPool(4);//只有核心线程nThreads个
    ExecutorService pool3 = Executors.newScheduledThreadPool(4);//带定时器的线程池
    ExecutorService pool4 = Executors.newCachedThreadPool();//没有核心线程，临时线程不限制

    public static void main(String[] args) {
        ExecutorService pool = new ThreadPoolExecutor(
                3,//核心线程数
                5,//最大线程数
                //核心线程处理任务处理不过来，就创建临时线程
                //临时线程：空闲时间超出时间范围，临时就退出
                30,//时间数量
                TimeUnit.SECONDS,//时间单位（时间数量 + 时间单位表示一定范围的时间）
                new ArrayBlockingQueue<>(1000),//阻塞队列 //存放任务的数据结构 //当任务量超出capacity时就调用拒绝策略
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return null;
                    }
                },//不作为传入参数也可以 //（了解）线程池创建Thread线程的工厂类。没有提供的话，就使用线程池内部默认的创建线程的方法

//                拒绝策略
//                callRunPolicy：哪个线程调用，就让线程自己运行（execute代码所在的线程）
//                AbortPolicy：直接抛出异常RejectedExecutionException
//                DiscardPolicy：从阻塞队列丢弃最新的任务
//                DiscardOldestPolicy：从阻塞队列丢弃最老的任务
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }
}
