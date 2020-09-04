package Thread_study;

public class MyThreadPool {

    private int size;
    private MyBlockQueue<Runnable> queue;

    public MyThreadPool(int size,int capacity){
        this.size = size;
        queue = new MyBlockQueue<>(capacity);
        //创建核心线程
        for(int i = 0;i < size;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //核心线程一直运行
                        while(true){
                            //取任务
                            //1.取出任务，方法返回
                            //2.取不出（阻塞在synchronized代码行或者wait方法阻塞了）
                            Runnable task = queue.poll();
                            //当前线程调用实例方法
                            task.run();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public void execute(Runnable task){
        try {
            queue.add(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
