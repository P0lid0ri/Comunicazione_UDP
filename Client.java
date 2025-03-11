package udptime;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {
    private int port = 2000;
    private InetAddress serverAddress;
    private DatagramSocket dSocket;

    public Client() {
        try {
            serverAddress = InetAddress.getLocalHost();
            dSocket = new DatagramSocket();
            System.out.println("Indirizzo del server trovato!");
        } catch (UnknownHostException e) {
            System.err.println("Errore DNS");
        } catch (SocketException e) {
            System.err.println("Errore Socket");
        }
    }

    public void richiesta() {
        try {
            String message = "RICHIESTA DATA E ORA";
            DatagramPacket outPacket = new DatagramPacket(message.getBytes(), message.length(), serverAddress, port);
            dSocket.send(outPacket);
            System.out.println("Richiesta inviata al server!");

            byte[] buffer = new byte[256];
            DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);
            dSocket.receive(inPacket);
            String response = new String(inPacket.getData(), 0, inPacket.getLength());

            System.out.println("Data e ora del server: " + response);

        } catch (IOException e) {
            System.err.println("Errore di I/O");
        } finally {
            dSocket.close();
            System.out.println("Comunicazione chiusa!");
        }
    }
}
