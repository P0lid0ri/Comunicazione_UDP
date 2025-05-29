package udptime;

public class MainServer {
    public static void main(String[] args) {
        Server server = new Server(2000);
        server.receive();
    }
}
