package com.example.chatclient;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
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
        label.setLayoutX(40);
        label.setLayoutY(pane.getChildren().getFirst().getLayoutY()-20);
        label.setMaxWidth(450);
        label.setWrapText(true);
        chatTextField.toFront();
        ReadMessages();
    }

    @FXML
    public void OnEnterPressed(ActionEvent e){
        OnMessageSent();
    }

    @FXML
    public void OnScroll(ScrollEvent s){
        var y = label.getLayoutY();
        System.out.println(y);
        if(s.getDeltaY() > 0){
            label.setLayoutY(y + 17);
        }else{
            label.setLayoutY(y - 17);
        }
    }

    public void OnMessageSent(){
        if(server == null){
            server = Data.GetInstance().GetServer();
        }

        var message = chatTextField.getText();

        if (message.isEmpty()) {
            return;
        }

        server.SendMessage(message);
        chatTextField.clear();
    }


    public void ReadMessages(){
        Thread t = new Thread(() -> {
            int next = 1;
            while(true){
                List<String> messages = Data.GetInstance().GetMessages();
                int size = messages.size();
                try {Thread.sleep(100);} catch (InterruptedException e) {}
                if(size > 0 && size == next){

                    next++;
                    var message = messages.get(size-1);

                    Platform.runLater(() -> {
                        var text = label.getText();
                        label.setText(text + "\n" + message);
                        label.setLayoutY(label.getLayoutY()-17);
                    });
                }
            }
        });

        t.start();
    }


}
