package 聊天小程序.th1_无GUI;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receive implements Runnable{

    // 4 用于接收数据的数据流
    private DataInputStream dis;
    private boolean flag = true;

    public Receive(Socket client){
        try {
            dis = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            flag = false;
            CloseUtil.closeAll(dis, client);
        }
    }

    public String getMessage(){
        try {
            return dis.readUTF();
        } catch (IOException e) {
            flag = false;
            CloseUtil.closeAll(dis);
        }
        return "";
    }

    @Override
    public void run() {
        while (flag){
            System.out.println(Thread.currentThread().getName() + ":" +getMessage());
        }
    }
}
