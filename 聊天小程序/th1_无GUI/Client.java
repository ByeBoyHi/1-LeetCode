package 聊天小程序.th1_无GUI;

import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        // 1 创建Socket对象
        Socket client = new Socket("localhost", 9999);

        // 创建发送的线程类对象
        Send send = new Send(client);
        // 创建接收的线程类对象
        Receive receive = new Receive(client);
        // 创建Thread类
        new Thread(send).start();
        new Thread(receive).start();

    }
}
