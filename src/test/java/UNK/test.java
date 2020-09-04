package UNK;

import org.junit.*;

public class test {
//    @BeforeClass //运行测试用例前
//    @AfterClass //运行测试用例后
    public static int i = 0;

    public int getI(int num){
        double result = 3;
        for(int i = 1;i < num - 1;i++){
            result /= 0.4;
        }
        return (int)result;
    }

    @Test
    public void s(){
        System.out.println(13 & 17);
    }

    @After
    public void after(){
        System.out.println("1");
    }

    @Before
    public void before(){
        System.out.println("9");
    }
}
