package Thread_study;

public class ThreadOfWord {
    private static volatile int INDEX;

    /**
     *
     * @param array 目标顺序输出数组
     * @param count 输出次数
     */
    private static void print(String[] array, int count) {
        for (int i = 0; i < array.length; i++) {
            final int k = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (ThreadOfWord.class) {
                        try {
                            for (int j = 0; j < count; j++) {
                                while ((INDEX % array.length) != k) {
                                    ThreadOfWord.class.wait();
                                }
                                INDEX++;
                                System.out.print(array[k]);
                                if (k == array.length - 1) {
                                    System.out.println();
                                }
                                ThreadOfWord.class.notifyAll();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }

    public static void main(String[] args) {
        print(new String[]{"A", "B", "C","D"}, 10);
        while (Thread.activeCount() > 1){
            Thread.yield();
        }
        print(new String[]{"D","E"}, 10);
    }
}
