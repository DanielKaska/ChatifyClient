package com.example.chatclient;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ChatController {
    @FXML
    private TextField chatTextField;

    @FXML
    private Pane pane;

    private Server server;

    Text text = new Text();
    static Label label = new Label();

    public void initialize(){
        label.setAlignment(Pos.CENTER);

        pane.getChildren().add(label);
        label.setVisible(true);
        label.setTextFill(Color.rgb(255, 199, 69));
        label.setLayoutX(25);
    }

    int j = 0;
    int size;

    public void OnMessageSent(){
        if(server == null){
            server = Data.GetInstance().GetServer();
            ReadMessages();
        }

        var message = chatTextField.getText();
        server.SendMessage(message);
    }


    List<Text> messages = new ArrayList<>();


    void AddTextLine(){
        text.setX(100);
        text.setY(400);
        text.setFill(Color.rgb(255, 199, 69));
        pane.getChildren().add(text);
    }

    public void ReadMessages(){
        AddTextLine();
        Thread t = new Thread(() -> {

            List<String> messages;

            while(true){
                messages = Data.GetInstance().GetMessages();
                size = messages.size();

                if(size > 0){
                    try{
                        var message = messages.get(j);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                var text = label.getText();
                                label.setText(text + "\n" + message);
                                System.out.println(label.getHeight());
                            }
                        });

                        j++;
                    }catch (Exception e) {

                    }

                }

            }

        });

        t.start();
    }


}
