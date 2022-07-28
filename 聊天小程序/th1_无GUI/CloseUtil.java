package 聊天小程序.th1_无GUI;

import java.io.Closeable;
import java.io.IOException;

public class CloseUtil {
    public static void closeAll(Closeable... arg) {
        for (Closeable o: arg){
            if (o!=null) {
                try {
                    o.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
