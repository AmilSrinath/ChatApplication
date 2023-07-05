package lk.ijse.chatapplication;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerFormContoller {

    /*static String message = "";
    public JFXTextArea txtAreaSever;
    public JFXTextField txtServer;
    public JFXButton btnServerSend;

    static ArrayList<ClientFormController> clients = new ArrayList<>();
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    private ServerSocket serverSocket;
    Socket socket;*/

    private static ArrayList<Clients> clientsArrayList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        /*launch(args);*/
        ServerSocket serverSocket = new ServerSocket(6000);
        Socket socket;
        int index = 1;
        while (true){
            System.out.println("Waiting for Client ...");
            socket = serverSocket.accept();
            System.out.println("Client "+index+" Connected");
            Clients clients = new Clients(socket,clientsArrayList);
            clientsArrayList.add(clients);
            clients.start();
            index++;
        }
    }

    /*public void OnActionServerSend(ActionEvent actionEvent) throws IOException {
        dataOutputStream.writeUTF(txtServer.getText().trim());
        dataOutputStream.flush();
        txtServer.clear();
    }*/

    /*@Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/ServerForm.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Server");
        stage.setScene(scene);
        stage.show();
    }*/

    /*public void btnAddOnAction(ActionEvent actionEvent) throws IOException {
        ClientFormController clientFormController = new ClientFormController(socket);
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/ClientForm.fxml"))));
        clients.add(clientFormController);
        stage.show();
    }*/
}
