package Thread_study;

public class BreadOperator {
    public static volatile int SUM;

    private static class Producer implements Runnable{
        @Override
        public void run() {
            try {
                for(int i = 0;i < 20;i++){
                    synchronized (BreadOperator.class){
                        while(SUM + 3 > 100){
                            BreadOperator.class.wait();
                        }
                        SUM = SUM + 3;
                        System.out.println(Thread.currentThread().getName() + "制造了," + "库存:" + SUM);
                        Thread.sleep(100);
                        BreadOperator.class.notifyAll();
                    }
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Consumer implements Runnable{
        @Override
        public void run() {
            try {
                while(true){
                    synchronized (BreadOperator.class) {
                        while (SUM - 1 < 0) {
                            BreadOperator.class.wait();
                        }
                        SUM--;
                        Thread.sleep(100);
                        System.out.println(Thread.currentThread().getName() + "消费了," + "库存:" +SUM);
                        BreadOperator.class.notifyAll();
                    }
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for(int i = 0;i < 5;i++){
            new Thread(new Producer(),"面包师傅" + i +"号:").start();
        }
        for(int i = 0;i < 20;i++){
            new Thread(new Consumer(),"顾客" + i + "号:").start();
        }
    }
}
