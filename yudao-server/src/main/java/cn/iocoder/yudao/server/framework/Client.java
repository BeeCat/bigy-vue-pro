package cn.iocoder.yudao.server.framework;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        Socket socket = null;
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;

        InetSocketAddress addr = new InetSocketAddress("127.0.0.1", 1001);
        try {
            socket = new Socket();
            socket.connect(addr);
            // 输入输出 和 io 进行绑定
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());

            outputStream.writeUTF("biguoyong");
            outputStream.flush();

            System.out.println(inputStream.readUTF());
        } catch (Exception e) {
            try {
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}
