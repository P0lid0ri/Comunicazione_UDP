package udptime;

import java.io.IOException;
import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Client {
    private int port = 2000;
    private InetAddress serverAddress;
    private DatagramSocket dSocket;

    private DatagramPacket outPacket;

    private DatagramPacket inPacket;


    public Client() {
        try {
            serverAddress = InetAddress.getLocalHost();

            System.out.println("Indirizzo del server trovato!");
        } catch (UnknownHostException e) {
            System.err.println("Errore DNS");

        }
    }

    public void richiesta() {
Scanner scanner = new Scanner(System.in);
while(true){
    System.out.print("Inserisci una stringa: ");
    String richiesta = scanner.nextLine();
    if(richiesta.equalsIgnoreCase("exit")){
        break;
    }
    send(richiesta);

}
scanner.close();
    }
    public void send(String message) {
        try {
            dSocket = new DatagramSocket();
        } catch (SocketException e) {
            System.err.println("Errore nel Socket");
        }
        outPacket = new DatagramPacket(message.getBytes(), message.length(), serverAddress, port);

        try {
            dSocket.send(outPacket);
            reiceve();
        } catch (IOException e) {
            System.err.println("Errore di invio");
        }


    }
    public void reiceve(){
        byte[]  bufferIn=new byte[256];

        inPacket=new DatagramPacket(bufferIn, bufferIn.length);

        try{
            dSocket.receive(inPacket);
        }catch(IOException e ){
            System.err.println("Errore");
        }

        String messageIn = new String(inPacket.getData(),0,inPacket.getLength());
        System.out.println("Messaggio Server: "+messageIn);



    }
}
