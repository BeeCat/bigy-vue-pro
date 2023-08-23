package cn.iocoder.yudao.server.framework;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRpc {

    public static final boolean running = true;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        try {

            while (running) {
                Socket socket = serverSocket.accept();
//                process(socket);
                socket.close();
            }
        } finally {
            serverSocket.close();
        }
    }

    private static void process(Socket socket) throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        ObjectInputStream ois = new ObjectInputStream(inputStream);
        String className = ois.readUTF();
        String methodName = ois.readUTF();
        Class[] agsType = (Class[])ois.readObject();
        Object[] ags = (Object[])ois.readObject();

        Class<?> aClass = Class.forName(className);
        if (aClass.isInterface()){
            if (className.equals("xx.xx.xx.xxService")) {
                aClass = UserServiceImpl.class;
            }
        }
        Method method = aClass.getMethod(methodName, agsType);
        Object obj = method.invoke(aClass.newInstance(), ags);

        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        oos.writeObject(obj);
        oos.flush();

    }
}
