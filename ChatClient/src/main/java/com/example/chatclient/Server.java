package com.example.chatclient;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Server {
    Socket clientSocket;
    BufferedReader reader;
    PrintWriter out;

    static List<String> messages = new ArrayList<String>();

    @FXML
    Label chatLabel;
    @FXML
    private TextField chatTextField;

    int i = 0;

    public void initialize(){
        //dokonczyc to gowno
    }

    public void OnMessageSent(){
        var message = chatTextField.getText();
        System.out.println(chatLabel);
        Controller.server.SendMessage(message);
    }

    void WriteMessage(String message){
        var text = chatLabel.getText();
        chatLabel.setText(message);
    }


    public void Connect(){
        try{
            clientSocket = new Socket("127.0.0.1", 8080);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            ReadMessages();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    void ReadMessages(){
        Thread t = new Thread(() -> {
            while(true){
                try{
                    var response = reader.readLine();
                    if(!response.equals("")){
                        messages.add(response);
                    }
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }

            }
        });

        t.start();
    }

    void SendMessage(String message){
        out.println(message);
    }

}
