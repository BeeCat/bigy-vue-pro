package cn.iocoder.yudao.server.framework;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class ClientRpc {

    public static Object getStub(final Class clazz) {

        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket socket = new Socket("127.0.0.1", 9999);
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                String name = clazz.getName();
                String methodName = method.getName();
                Class<?>[] parameterTypes = method.getParameterTypes();
                outputStream.writeUTF(name);
                outputStream.writeObject(methodName);
                outputStream.writeObject(parameterTypes);
                outputStream.flush();

                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                Object o = inputStream.readObject();
                outputStream.close();
                socket.close();
                return o;
            }
        };

        Object o = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, handler);
        return o;

    }
}
