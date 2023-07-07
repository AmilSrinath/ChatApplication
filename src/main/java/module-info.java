module com.example.chatapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.sql;


    opens lk.ijse.chatapplication to javafx.fxml;
    exports lk.ijse.chatapplication;
}