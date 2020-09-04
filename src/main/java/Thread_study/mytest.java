package Thread_study;

public class mytest {
    public static final int NUM = 20;

    public static volatile int COUNT;

//    public static final String string = "URL";

    public static synchronized void increment(){
        COUNT++;
    }

    public static void main(String[] args) throws InterruptedException {
        final int sum = 0;
        System.out.println(mytest.class.getClassLoader());
        for(int i = 0;i < NUM;i++){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0;i < 10000;i++){
                        //increment();
                    }
                }
            });
            t.start();
//            t.join();
        }
        while(Thread.activeCount() > 1){
            Thread.yield();
        }
        System.out.println(COUNT);
    }
}
