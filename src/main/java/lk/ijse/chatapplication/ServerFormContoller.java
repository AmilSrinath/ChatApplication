package lk.ijse.chatapplication;

import javafx.event.ActionEvent;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerFormContoller {

    private static ArrayList<Clients> clientsArrayList = new ArrayList<>();

    public static void Server() throws IOException {
        ServerSocket serverSocket = new ServerSocket(6000);
        Socket socket;
        int index = 1;
        while (true) {
            System.out.println("Waiting for Client ...");
            socket = serverSocket.accept();
            System.out.println("Client " + index + " Connected");
            Clients clients = new Clients(socket, clientsArrayList);
            clientsArrayList.add(clients);
            clients.start();
            index++;
        }
    }
}
