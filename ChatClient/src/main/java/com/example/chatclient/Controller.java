package com.example.chatclient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Application;



import java.io.IOException;

public class Controller {
    @FXML
    private TextField textField;
    @FXML
    private Button confirmButton;

    public static Server server;

    Stage window;

    public void OnConfirmEnter(){
        confirmButton.setStyle("-fx-background-color: #d69f20; -fx-border-radius: 15; -fx-background-radius: 15; -fx-border-color: #d69f20;");
    }

    public void OnConfirmExit(){
        confirmButton.setStyle("-fx-background-color: #ffc745; -fx-border-radius: 15; -fx-background-radius: 15; -fx-border-color: #ffc745;");
    }

    public void OnButtonClicked() throws IOException {
        server = new Server();
        var nick = textField.getText();
        server.Connect();
        server.SendMessage(nick);
        Parent root = FXMLLoader.load(App.class.getResource("Chat.fxml"));

        window = (Stage) confirmButton.getScene().getWindow();
        window.setScene(new Scene(root));
        window.setTitle("Chatting as: " + nick);
        window.setResizable(false);

    }




}