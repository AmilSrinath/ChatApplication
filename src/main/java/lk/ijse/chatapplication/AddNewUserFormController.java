package lk.ijse.chatapplication;

import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AddNewUserFormController extends Application {
    public JFXTextField txtUsername;
    static String username;
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/AddNewUserForm.fxml"))));
        stage.show();

        new Thread(()->{
            ServerFormContoller serverFormContoller = new ServerFormContoller();
            try {
                serverFormContoller.Server();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws IOException {
        username = txtUsername.getText();
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/ClientForm.fxml"))));
        stage.setTitle(txtUsername.getText() + "'s Chat");
        stage.show();
        txtUsername.clear();
    }
}
