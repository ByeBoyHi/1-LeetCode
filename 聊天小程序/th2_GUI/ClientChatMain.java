package 聊天小程序.th2_GUI;

import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.Socket;
import java.util.Properties;

/**
 * 说明：如果一个类需要有界面的形式，那么这个类需要继承JFrame，该类就被称为窗体类
 */
public class ClientChatMain extends JFrame implements ActionListener, KeyListener {

    public static void main(String[] args) {
        new ClientChatMain();
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
    // 输出流：用于输出到文本框里面
    private BufferedWriter bw = null;
    // 客户端的IP地址和端口号
    private static String ip;
    private static int port;


    // 读取文件：使用static静态代码块读取外部配置文件
    // 静态代码块：
    //  特点1：在类加载的时候，自动执行
    //  特点2：一个类只会被加载一次，因此静待代码块仅会被执行一次
    static {
        Properties prop = new Properties();
        try {
            prop.load(ClientChatMain.class.getResourceAsStream("chat.properties"));
            ip = prop.getProperty("ClientIp");
            port = Integer.parseInt(prop.getProperty("ClientPort"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ClientChatMain(){
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
        this.setTitle("QQ聊天 Client");
        this.setSize(300,300);
        this.setLocation(600,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        /* ****************TCP 客户端 start**************** */
        // 给发送按钮绑定监听事件
        jb.addActionListener(this);
        // 监听回车事件
        jtf.addKeyListener(this);
        try {
            // 1. 创建一个客户端的套接字（尝试连接）
            Socket socket = new Socket(ip,port);
            // 2. 获取 socket 通道的输出流
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            // 3. 循环获取 socket 通道的输入流，并拼接到文本域中
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line=br.readLine())!=null){
                jta.append(line + System.lineSeparator());
            }
            // 4. 关闭通道
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* ****************TCP 客户端 end  **************** */

    }

    @SneakyThrows
    public void send(){
        // 1. 获取文本框中需要发送的内容
        String text = jtf.getText();
        // 2. 拼接内容
        String text1 = "Client to You : " + text;
        String text2 = "You to Server : " + text;
        // 3. 自己显示
        jta.append(text2 + System.lineSeparator());
        // 4. 发送内容
        bw.write(text1);
        bw.newLine();
        bw.flush();
        // 5. 清空
        jtf.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        send();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER){
            send();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    // 行为
}
