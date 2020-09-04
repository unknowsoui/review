package Thread_study;

/**
 * 满足线程安全的循环队列
 */
public class MyBlockQueue<E> {
    private Object[] items;
    private int pollindex;
    private int addindex;
    private int size;
    
    public MyBlockQueue(int size){
        items = new Object[size];
    }


    public synchronized E poll() throws InterruptedException {
        while (size == 0){
            wait();
        }
        pollindex = (pollindex + 1) % items.length;
        size--;
        notifyAll();
        return (E) items[pollindex];
    }

    public synchronized void add(E e) throws InterruptedException {
        while (size == items.length){
            wait();
        }
        addindex = (addindex + 1) % items.length;
        items[addindex] = e;
        size++;
        notifyAll();
    }

    public static void main(String[] args) {
        MyBlockQueue<Runnable> myBlockQueue = new MyBlockQueue(100);
    }
}
