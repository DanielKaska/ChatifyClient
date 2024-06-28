package com.example.chatclient;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ChatController {
    @FXML
    private TextField chatTextField;

    private Server server;

    public void initialize(){

    }

    public void OnMessageSent(){
        if(server == null){
            server = Data.GetInstance().GetServer();
        }
        server.SendMessage(chatTextField.getText());
    }

}
