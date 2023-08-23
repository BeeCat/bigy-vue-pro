package cn.iocoder.yudao.server.framework;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(1001));
        System.out.println("server start ...");

        while (true) {
            new Thread(new ServerSocketTask(serverSocket.accept())).start();
        }

    }

    private static class ServerSocketTask implements Runnable {
        private Socket socket = null;
        public ServerSocketTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {

            try (
                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                    ){
                String name = inputStream.readUTF();
                System.out.println(Thread.currentThread().getName() + " accept : " + name);
                outputStream.writeUTF(Thread.currentThread().getName() + " hello bigy ");
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
