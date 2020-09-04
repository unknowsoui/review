package Thread_study;

public class interruptThread {
    public static void test1(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){

                }
            }
        });
        t.start();
        t.interrupt();
    }

    public static void test2(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //while(!Thread.interrupted())
                while(!Thread.currentThread().isInterrupted()){
                    System.out.println(Thread.currentThread().getName());
                }
            }
        });
        t.start();//中断标志位 = false
        t.interrupt();//只是标记，中不中断由线程自己处理 //中断标志位 = true
    }

    public static void test3(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //线程调用wait()/join()/sleep()阻塞时，如果把当前线程中断，会直接抛异常
                    //阻塞状态时，通过捕获及处理异常，来处理线程的中断逻辑
                    //抛出异常后中断标志位会重置
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        t.interrupt();
    }
    public static void main(String[] args) {
        test3();
    }
}
