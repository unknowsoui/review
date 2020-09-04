package IO;

import org.junit.Test;

import java.io.*;

public class FileOperator {
    @Test
    public void test1() throws IOException {
        FileInputStream fis = new FileInputStream(new File("F:\\test\\hhx4.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(fis,"GBK"));
        String line;
        while((line = br.readLine()) != null){
            System.out.println(line);
        }
        br.close();
        fis.close();
    }

    @Test
    public void test2() throws IOException {
        FileOutputStream fos = new FileOutputStream(new File("F:\\test\\hhx4.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos,"GBK"));
        bw.write("1.test\n");
        bw.write("3.wu\n");
        bw.flush();
        bw.close();
        fos.close();
    }

    @Test
    public void copyFile() throws IOException {
        FileInputStream fis = new FileInputStream(new File("F:\\After the Rain.mp3"));
        BufferedInputStream br = new BufferedInputStream(fis);
        FileOutputStream fos = new FileOutputStream(new File("F:\\test\\copy.mp3"));
        BufferedOutputStream bw = new BufferedOutputStream(fos);
        byte[] bytes = new byte[1024 * 10];
        while(br.read(bytes) != -1){
            bw.write(bytes);
            bw.flush();
        }
    }
    public static void main(String[] args) {

    }
}
