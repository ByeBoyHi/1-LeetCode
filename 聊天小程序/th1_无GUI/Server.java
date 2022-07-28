package 聊天小程序.th1_无GUI;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        // 创建ServerSocket对象
        ServerSocket server = new ServerSocket(9999);
        int index = 1;
        while (true){
            Socket socket = server.accept();
            // 创建线程类的对象
            MyChannel channel = new MyChannel(socket);
            // 启动线程
            new Thread(channel,"Thread"+index++).start();
        }
    }
}
