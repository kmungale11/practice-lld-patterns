package patterns;

interface BaseServer {
    public void connect();

}

class ServerA implements BaseServer {

    @Override
    public void connect() {
        System.out.println("Connected to server A");
    }
}

class Proxy implements BaseServer {

    ServerA server;
    public Proxy() {
        server = new ServerA();
    }

    public void doPreProcessing() {
        System.out.println("PreProcessing Data in proxy");
    }
    @Override
    public void connect() {
        doPreProcessing();
        if(server != null) {
            server.connect();
        }
    }
}
public class Test {

    public static void main(String[] args) {
        BaseServer proxy = new Proxy();
        proxy.connect();
    }

}