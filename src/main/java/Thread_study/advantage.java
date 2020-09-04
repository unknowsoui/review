package Thread_study;

public class advantage {
    private static final int NUM = 5;

    private static void increment(){
        int count = 10_0000_0000;
        int r = 0;
        for(int i = 0;i < count;i++){
            r++;
        }
    }

    /**
     *  串行（代码依次执行）
     */
    private static void serial(){
        long start = System.currentTimeMillis();//1970-01-01开始，到当前时间毫秒数；
        for (int i = 0;i < NUM;i++){
            increment();
        }
        long end = System.currentTimeMillis();
        System.out.println("串行时间：" + (end - start));
    }

    /**
     * 并发（有时候java语义，并发有时候又表示并发也可以表示并行）
     */
    private static void parallel(){
        long start = System.currentTimeMillis();
        for (int i = 0;i < NUM;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    increment();
                }
            }).start();
        }
        while(Thread.activeCount() > 1){
            Thread.yield();
        }
        long end = System.currentTimeMillis();
        System.out.println("并行时间：" + (end - start));
    }

    public static void main(String[] args) {
        serial();
        parallel();
    }
}
