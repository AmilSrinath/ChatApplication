package com.example.chatapplication;

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

public class ServerFormContoller extends Application {
    static String message = "";

    public JFXTextArea txtAreaSever;
    public JFXTextField txtServer;
    public JFXButton btnServerSend;

    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;

    ServerSocket serverSocket;
    Socket socket;

    public static void main(String[] args)  {
        launch(args);
    }
    public void initialize(){
        new Thread(()->{
            try {
                serverSocket = new ServerSocket(4005);
                txtAreaSever.appendText("Client Started!");
                socket = serverSocket.accept();
                txtAreaSever.appendText("Client Accepted!");
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                while (!message.equals("finish")){
                    message = dataInputStream.readUTF();
                    txtAreaSever.appendText("\nClient : "+message);
                }
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }).start();
    }
    public void OnActionServerSend(ActionEvent actionEvent) throws IOException {
        dataOutputStream.writeUTF(txtServer.getText().trim());
        dataOutputStream.flush();
        txtServer.clear();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/ServerForm.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Server");
        stage.setScene(scene);
        stage.show();
    }
}
