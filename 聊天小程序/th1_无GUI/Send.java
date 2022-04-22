package 聊天小程序.th1_无GUI;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Send implements Runnable{

    // 2 从键盘获取数据
    private BufferedReader br;
    // 3 发送数据的数据流
    private DataOutputStream dos;
    private boolean flag = true;

    public Send(){
        br = new BufferedReader(new InputStreamReader(System.in));
    }
    public Send(Socket client) {
        this();
        try {
            dos = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            flag = false;
            CloseUtil.closeAll(dos, br, client);
        }
    }

    private String getMessage(){
        try {
            return br.readLine();
        } catch (IOException e) {
            CloseUtil.closeAll(br);
            flag = false;
        }
        return "";
    }

    private void send(String str){
        try {
            dos.writeUTF(str);
            dos.flush();
        } catch (IOException e) {
            flag =false;
            CloseUtil.closeAll(dos);
        }
    }


    @Override
    public void run() {
        while (flag){
            // 只要没有出错，就一直调用
            this.send(getMessage());
        }
    }
}
