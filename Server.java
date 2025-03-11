package udptime;

import java.io.IOException;
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;

public class Server {
    private int port = 2000;
    private DatagramSocket dSocket;

    public Server() {
        try {
            dSocket = new DatagramSocket(port);
            System.out.println("Apertura porta in corso!");
        } catch (BindException e) {
            System.err.println("Errore porta già in uso");
        } catch (SocketException e) {
            System.err.println("Errore Socket");
        }
    }

    public void ascolta() {
        try {
            while (true) {
                System.out.println("Server in ascolto sulla porta " + port + "!");
                byte[] bufferIn = new byte[256];

                DatagramPacket inPacket = new DatagramPacket(bufferIn, bufferIn.length);
                dSocket.receive(inPacket);

                InetAddress clientAddress = inPacket.getAddress();
                int clientPort = inPacket.getPort();
                String messageIn = new String(inPacket.getData(), 0, inPacket.getLength());
                System.out.println("SONO IL CLIENT " + clientAddress + ":" + clientPort + "> " + messageIn);

                String messageOut = new Date().toString();
                byte[] bufferOut = messageOut.getBytes();
                DatagramPacket outPacket = new DatagramPacket(bufferOut, bufferOut.length, clientAddress, clientPort);
                dSocket.send(outPacket);
                System.out.println("Risposta inviata!");
            }
        } catch (IOException e) {
            System.err.println("Errore di I/O");
        }
    }
}
