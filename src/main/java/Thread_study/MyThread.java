package Thread_study;

class MThread extends Thread {
    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName());
    }
}

class MThread1 implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

public class MyThread{
    public static void main(String[] args) {

    }
}
