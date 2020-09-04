package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT = 9000;

    private static final ExecutorService POOL = Executors.newCachedThreadPool();

    private static class Task implements Runnable{
        private Socket client;

        public Task(Socket socket){
            this.client = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter pw = new PrintWriter(client.getOutputStream(),true);
                String str;
                while((str = br.readLine()) != null){
                    System.out.println(str);
                    pw.println("2");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        while (true){
            //阻塞等待，知道有新的客户端连接
            Socket client = serverSocket.accept();
            POOL.execute(new Task(client));
        }
    }
}
