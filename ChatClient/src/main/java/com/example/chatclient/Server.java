package com.example.chatclient;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Server {
    Socket clientSocket;
    BufferedReader reader;
    PrintWriter out;

    private String nick;

    public List<String> messages = new ArrayList<String>();

    public Server(String nick){
        this.nick = nick;
        Connect();
        System.out.println(nick);
    }

    public void Connect(){
        try{
            clientSocket = new Socket("127.0.0.1", 8080);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            ReadMessages();

            out.println(nick);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void SendMessage(String message){
        out.println(message);
    }

    void ReadMessages(){
        Thread t = new Thread(() -> {
            while(true){
                try{
                    var response = reader.readLine();
                    if(!response.equals("")){
                        System.out.println(response);
                        Data.GetInstance().AddMessage(response);
                    }
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }

            }


        });

        t.start();
    }


}
