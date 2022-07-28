package 聊天小程序.th1_无GUI;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 每一个客户端都是一条道路
 * 1. 输入流
 * 2. 输出流
 * 3. 接收数据
 * 4. 发送数据
 */
public class MyChannel implements Runnable{
    private DataInputStream dis;
    private DataOutputStream dos;
    private boolean flag = true;


    public MyChannel(Socket socket){
        try {
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            flag = false;
            CloseUtil.closeAll(dis, dos);
        }
    }

    // 接收数据
    private String receive(){
        String str = "";
        try {
            str = dis.readUTF();
        } catch (IOException e) {
            flag = false;
            CloseUtil.closeAll(dis);
        }
        return str;
    }

    // 发送数据
    private void send(String str){
        if (str!=null){
            try {
                dos.writeUTF(str);
            } catch (IOException e) {
                flag = false;
                CloseUtil.closeAll(dos);
            }
        }
    }

    @Override
    public void run() {
        while (flag){
            send(receive());
        }
    }
}
