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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientForm2Controller extends Application {
    public JFXTextArea txtAreaClient;
    public JFXTextField txtClient;
    public JFXButton btnClientSend;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    Socket socket2;
    static String message = "";

    public static void main(String[] args) {
        launch(args);
    }
    public void initialize(){
        new Thread(()->{
            try {
                socket2 = new Socket("localhost",4006);
                dataInputStream = new DataInputStream(socket2.getInputStream());
                dataOutputStream = new DataOutputStream(socket2.getOutputStream());
                while (!message.equals("finish")){
                    message = dataInputStream.readUTF();
                    txtAreaClient.appendText("\nServer : "+message);
                }
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }).start();
    }
    public void OnActionClientSend(ActionEvent actionEvent) throws IOException {
        dataOutputStream.writeUTF(txtClient.getText().trim());
        dataOutputStream.flush();
        txtClient.clear();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("/View/ClientForm.fxml"));
        Scene mainScene = new Scene(root);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Client");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
