package cn.iocoder.yudao.server.framework;

public class RpcDemo {


    public static void main(String[] args) {
        ClientRpc.getStub(IUserService.class);
    }

}
