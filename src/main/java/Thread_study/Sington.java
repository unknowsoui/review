package Thread_study;

/**
 * 饿汉式单例模式
 */
class Sington1 {
    private static Sington1 SINGTON = new Sington1();

    private Sington1(){

    }

    public static Sington1 getSINGTON(){
        return SINGTON;
    }
}

/**
 * 懒汉式单例模式 单线程
 */
class Sington2{
    private static Sington2 SINGTON2;

    private Sington2(){

    }

    public static Sington2 getSington2(){
        if(SINGTON2 == null){
            SINGTON2 = new Sington2();
        }
        return SINGTON2;
    }
}

/**
 * 懒汉式单例模式 多线程安全 双重校验锁
 */
class Sington3{
    private static volatile Sington3 SINGTON3;

    private Sington3(){

    }

    public static Sington3 getSINGTON3(){
        if(SINGTON3 == null){
            synchronized (Sington3.class){
                //保证返回的是同一个对象
                if(SINGTON3 == null){
                    //new对象分解成三条指令
                    //1.分配内存空间
                    //2.初始化对象
                    //3.赋值变量
                    SINGTON3 = new Sington3();
                }
            }
        }
        return SINGTON3;
    }
}


