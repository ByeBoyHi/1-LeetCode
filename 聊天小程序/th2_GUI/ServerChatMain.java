package 聊天小程序.th2_GUI;

import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.Properties;

/**
 * 说明：如果一个类需要有界面的形式，那么这个类需要继承JFrame，该类就被称为窗体类
 */
// 1. 定义JFrame窗体中的组件
// 2. 在构造方法中初始化窗体的组件
// 3. 使用网络编程完成数据的传输（TCP UDP 协议）
// 4. 实现'发送'按钮的监听点事件
// 5. 实现回车键发送数据
public class ServerChatMain extends JFrame implements ActionListener, KeyListener {

    public static void main(String[] args) {
        new ServerChatMain();
    }

    // 属性
    // 文本域
    private JTextArea jta;
    // 滚动条
    private JScrollPane jsp;
    // 面板
    private JPanel jp;
    // 文本框
    private JTextField jtf;
    // 按钮
    private JButton jb;
    // 输出流：用于输出数据到面板
    private BufferedWriter bw = null;
    // 服务端的端口号
    private static int port;
    static {
        Properties prop = new Properties();
        try {
            prop.load(ServerChatMain.class.getResourceAsStream("chat.properties"));
            port = Integer.parseInt(prop.getProperty("ServerPort"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerChatMain() {
        jta = new JTextArea();
        // 设置文本域默认不可编辑
        jta.setEditable(false);
        jsp = new JScrollPane(jta);
        jp = new JPanel();
        jtf = new JTextField(10);  // 文本框的长度
        jb = new JButton("发送");

        jp.add(jtf);
        jp.add(jb);

        // 需要将上面的组件都添加到窗体里面
        this.add(jsp, BorderLayout.CENTER);
        this.add(jp, BorderLayout.SOUTH);
        this.setTitle("QQ聊天 Server");
        this.setSize(300,300);
        this.setLocation(200,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        /* ****************TCP 服务端 start**************** */
        // 给发送按钮绑定监听事件
        jb.addActionListener(this);
        // 我们在写东西的时候，需要回车键然后发送数据，所以给文本框绑定键盘事件
        jtf.addKeyListener(this);
        try {
            // 1. 创建一个服务端的套接字
            ServerSocket serverSocket = new ServerSocket(port);
            // 2. 等待客户端的链接
            Socket socket = serverSocket.accept();
            // 3. 获取 socket 通道的输出流（实现输出数据，使用BufferedWriter -> newLine()）
            // 问题：什么时候需要写出数据？当用户点击`发送`按钮的时候才需要写出数据
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            // 4. 获取 socket 通道的输入流（实现输入数据，使用BufferedReader 的 readLine 可以实现一行一行读取）
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = br.readLine())!=null){
                // 将读取的数据拼接到文本域中显示
                jta.append(line + System.lineSeparator());  // 加一个换行
            }
            // 5. 关闭通道
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* ****************TCP 服务端 end  **************** */
    }

    @SneakyThrows
    public void send(){
        // 1. 获取文本框中发送的内容
        String text = jtf.getText();
        // 2. 拼接需要发送的数据内容
        String text1 = "You to Client : " + text;
        String text2 = "Server to You : " + text;
        // 3. 自己有需要显示
        jta.append(text1 + System.lineSeparator());
        bw.write(text2);
        bw.newLine();
        bw.flush();
        // 5. 清空文本框
        jtf.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        send();
    }

    // 按下：我们是实现回车发送信息，所以实现按下
    @Override
    public void keyPressed(KeyEvent e) {
        // 按回车键的时候触发事件
        if (e.getKeyCode()==KeyEvent.VK_ENTER){
            // 发送数据到套接字里面
            send();
        }
    }

    // 被敲击
    @Override
    public void keyTyped(KeyEvent e) {

    }

    // 松开
    @Override
    public void keyReleased(KeyEvent e) {

    }

    // 行为
}
